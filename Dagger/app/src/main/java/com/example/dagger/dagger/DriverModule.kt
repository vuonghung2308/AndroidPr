package com.example.dagger.dagger

import com.example.dagger.car.Driver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DriverModule {
    companion object {
        @Provides
        @Singleton
        fun provideDriver(): Driver {
            return Driver()
        }
    }
}