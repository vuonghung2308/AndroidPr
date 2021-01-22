package com.example.cleanarchitecture

import android.app.Application
import com.example.cleanarchitecture.di.appModule
import com.example.cleanarchitecture.di.presentationModule
import com.example.data.di.databaseModule
import com.example.data.di.networkingModule
import com.example.data.di.repositoryModule
import com.example.domain.di.interactionModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(appModules + domainModules + dataModules)
        }
    }

}

val appModules = listOf(presentationModule, appModule)
val domainModules = listOf(interactionModule)
val dataModules = listOf(networkingModule, repositoryModule, databaseModule)