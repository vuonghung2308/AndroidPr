package com.example.dagger.dagger

import com.example.dagger.car.Rims
import com.example.dagger.car.Tires
import com.example.dagger.car.Wheels
import dagger.Module
import dagger.Provides

@Module
class WheelsModule {
    companion object {
        @Provides
        fun provideRims(): Rims {
            return Rims()
        }

        @Provides
        fun provideTires(): Tires {
            val tires = Tires()
            tires.inflate()
            return tires
        }

        @Provides
        fun provideWheels(rims: Rims, tires: Tires): Wheels {
            return Wheels(rims, tires)
        }
    }
}