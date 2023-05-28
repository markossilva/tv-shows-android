package br.com.mks.tvshows.data

import android.content.Context

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {

}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

}