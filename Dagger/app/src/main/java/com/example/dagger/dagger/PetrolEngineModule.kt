package com.example.dagger.dagger

import com.example.dagger.car.Engine
import com.example.dagger.car.PetrolEngine
import dagger.Binds
import dagger.Module

@Module
abstract class PetrolEngineModule {
    @Binds
    abstract fun bindEngine(petrolEngine: PetrolEngine): Engine
}