package com.example.dagger.dagger

import com.example.dagger.car.Driver
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DriverModule::class])
@Singleton
interface AppComponent {
    fun getDriver(): Driver
}