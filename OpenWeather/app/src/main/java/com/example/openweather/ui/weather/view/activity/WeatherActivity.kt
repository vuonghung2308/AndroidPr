package com.example.openweather.ui.weather.view.activity

import android.os.Bundle
import com.example.openweather.R
import com.example.openweather.routing.ActivityNavigatorImpl
import com.example.openweather.ui.ScreenType
import com.example.openweather.ui.base.BaseActivity
import com.example.openweather.ui.weather.view.fragment.WeatherFragment

class WeatherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        when (intent.getSerializableExtra(ActivityNavigatorImpl.SCREEN_TYPE)) {
            ScreenType.WEATHER -> addFragment(
                R.id.fragmentContainer,
                WeatherFragment.newInstance(),
                true
            )
            else -> finish()
        }
    }
}