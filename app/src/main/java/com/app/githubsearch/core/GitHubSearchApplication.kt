package com.app.githubsearch.core

import android.app.Application
import com.app.githubsearch.core.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GitHubSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GitHubSearchApplication)
            modules(appModules)
        }
    }
}