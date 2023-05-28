package br.com.mks.tvshows.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import br.com.mks.tvshows.TvShowsApplication.Companion.TVSHOWS_APP_URI
import br.com.mks.tvshows.data.AppContainer

const val POST_ID = "postId"

@Composable
fun TvShowsNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = TvShowsDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = TvShowsDestinations.HOME_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern =
                        "$TVSHOWS_APP_URI/${TvShowsDestinations.HOME_ROUTE}?$POST_ID={$POST_ID}"
                }
            )
        ) {
            Log.d("TvShowsNavGraph", "chegamos aqui!")
        }
    }
}