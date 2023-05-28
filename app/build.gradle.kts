plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "br.com.mks.tvshows"

    defaultConfig {
        applicationId = "br.com.mks.tvshows"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }


    signingConfigs {
        // We use a bundled debug keystore, to allow debug builds from CI to be upgradable
        named("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    // Tests can be Robolectric or instrumented tests
    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        getByName("test") {
            java.srcDir(sharedTestDir)
        }
        getByName("androidTest") {
            java.srcDir(sharedTestDir)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    buildToolsVersion = "33.0.2"
    packaging.resources {
        // Multiple dependency bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        excludes += "/META-INF/AL2.0"
        excludes += "/META-INF/LGPL2.1"
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.core.core.ktx) // ("androidx.core:core-ktx:1.8.0")
    implementation(libs.androidx.lifecycle.lifecycle.runtime.ktx) //("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation(libs.androidx.activity.activity.compose) //("androidx.activity:activity-compose:1.5.1")
    implementation(libs.androidx.compose.ui) //("androidx.compose.ui:ui")
    implementation(libs.androidx.compose.ui.ui.graphics) // ("androidx.compose.ui:ui-graphics")
    implementation(libs.androidx.compose.ui.ui.tooling.preview) //("androidx.compose.ui:ui-tooling-preview")
    implementation(libs.androidx.compose.material3) //("androidx.compose.material3:material3")
    testImplementation(libs.junit) //("junit:junit:4.13.2")
    androidTestImplementation(libs.androidx.test.ext.junit) //("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(libs.androidx.test.espresso.espresso.core)// ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4) //("androidx.compose.ui:ui-test-junit4")
    debugImplementation(libs.androidx.compose.ui.ui.tooling) //("androidx.compose.ui:ui-tooling")
    debugImplementation(libs.androidx.compose.ui.ui.test.manifest) // ("androidx.compose.ui:ui-test-manifest")
}

tasks.withType<Test>().configureEach {
    systemProperties.put("robolectric.logging", "stdout")
}