package com.example.dagger

import android.app.Application
import com.example.dagger.dagger.ActivityComponent
import com.example.dagger.dagger.AppComponent
import com.example.dagger.dagger.DaggerAppComponent

class App : Application() {
    private lateinit var _component: AppComponent
    val activityComponent: AppComponent
        get() = _component

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        _component = DaggerAppComponent.create()
//        _component = DaggerCarComponent.builder()
//            .engineCapacity(150)
//            .horsePower(100)
//            .build()
    }

    companion object {
        private lateinit var INSTANCE: App
        fun get(): App = INSTANCE
    }
}