package br.com.mks.tvshows

import android.app.Application
import br.com.mks.tvshows.data.AppContainer
import br.com.mks.tvshows.data.AppContainerImpl

class TvShowsApplication : Application() {
    companion object {
        const val TVSHOWS_APP_URI = "https://developer.android.com/tvshows"
    }

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}