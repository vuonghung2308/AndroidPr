package com.example.dagger.car

import android.util.Log
import com.example.dagger.dagger.PerActivity
import javax.inject.Inject

@PerActivity
class Car @Inject constructor(
    private var wheels: Wheels,
    private var engine: Engine,
    private var driver: Driver
) {

    companion object {
        private const val TAG = "Car"
    }

    @Inject
    fun enableRemote(remote: Remote) {
        remote.setListener(this);
    }

    fun drive() {
        engine.start()
        Log.d(TAG, "drive: driving... Driver: " + driver + "Car: " + this)
    }
}