package com.example.dagger.car

import android.util.Log

class Tires {
    companion object {
        private const val TAG = "Tires"
    }
    fun inflate() {
        Log.d(TAG, "inflate: inflated")
    }
}