package com.example.cleanarchitecture.ui.weather.view.activities

import android.os.Bundle
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.routing.AppNavigator
import com.example.cleanarchitecture.routing.ScreenType
import com.example.cleanarchitecture.ui.base.BaseActivity
import com.example.cleanarchitecture.ui.weather.view.fragments.WeatherFragment

class WeatherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        when (intent.getSerializableExtra(AppNavigator.SCREEN_TYPE)) {
            ScreenType.WEATHER -> addFragment(
                WeatherFragment.newInstance(),
                R.id.fragmentContainer,
                false
            )
            else -> finish()
        }
    }
}