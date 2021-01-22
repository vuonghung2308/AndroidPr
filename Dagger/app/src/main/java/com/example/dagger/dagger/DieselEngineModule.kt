package com.example.dagger.dagger

import com.example.dagger.car.DieselEngine
import com.example.dagger.car.Engine
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DieselEngineModule(
    private val horsePower: Int
) {
    @Provides
    fun provideHorsePower(): Int {
        return horsePower
    }
    @Provides
    fun provideEngine(engine: DieselEngine): Engine {
        return engine
    }
}