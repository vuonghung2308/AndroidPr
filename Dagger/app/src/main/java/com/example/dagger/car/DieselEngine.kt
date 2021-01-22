package com.example.dagger.car

import android.util.Log
import javax.inject.Inject

class DieselEngine @Inject constructor(
    private val horsePower: Int
): Engine {
    override fun start() {
        Log.d(TAG, "start: engine started, horse power: " + horsePower)
    }
    companion object {
        private const val TAG = "DieselEngine";
    }
}