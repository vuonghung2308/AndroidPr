package com.example.githubapi

import android.app.Application
import com.example.githubapi.di.AppComponent
import com.example.githubapi.di.DaggerAppComponent

class App : Application() {
    private lateinit var _component: AppComponent
    val component: AppComponent
        get() = _component

    override fun onCreate() {
        super.onCreate()
        initComponent()
        app = this
    }

    fun initComponent() {
        _component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        private lateinit var app: App
        fun get() = app
    }

}