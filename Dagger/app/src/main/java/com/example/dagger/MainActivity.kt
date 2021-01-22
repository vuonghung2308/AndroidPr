package com.example.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dagger.car.Car
import com.example.dagger.dagger.DaggerActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var car1: Car

    @Inject
    lateinit var car2: Car
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerActivityComponent.builder()
            .horsePower(100)
            .engineCapacity(150)
            .appComponent(App.get().activityComponent)
            .build().inject(this)
        car1.drive()
        car2.drive()
    }
}