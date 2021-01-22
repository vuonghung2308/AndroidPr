package com.example.openweather

import android.app.Application
import com.example.data.di.databaseModule
import com.example.data.di.networkingModule
import com.example.data.di.repositoryModule
import com.example.domain.di.interactionModule
import com.example.openweather.di.appModule
import com.example.openweather.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG)
                androidLogger(Level.DEBUG)
            modules(appModules + dataModules + domainModules)
        }
    }

    companion object {
        lateinit var instance: Application
            set
        val appModules = listOf(appModule, presentationModule)
        val dataModules = listOf(networkingModule, databaseModule, repositoryModule)
        val domainModules = listOf(interactionModule)
    }
}