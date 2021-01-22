package com.example.dagger.car

import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class PetrolEngine @Inject constructor(
    @Named("horse power") private val horsePower: Int,
    @Named("engine capacity") private val engineCapacity: Int
) : Engine {
    override fun start() {
        Log.d(
            TAG,
            "start: engine started, Horsepower: " + horsePower + ", engine capacity: " + engineCapacity
        )
    }

    companion object {
        private const val TAG = "PetrolEngine";
    }
}