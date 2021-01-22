package com.example.openweather.ui.welcome.activity

import android.os.Bundle
import com.example.openweather.R
import com.example.openweather.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        nextButton.setOnClickListener {
            navigator.showWeatherActivity()
        }
    }
}