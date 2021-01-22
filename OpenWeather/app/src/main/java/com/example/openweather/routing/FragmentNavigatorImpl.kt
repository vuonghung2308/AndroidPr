package com.example.openweather.routing

import androidx.fragment.app.FragmentActivity
import com.example.openweather.R
import com.example.openweather.common.extension.showFragment
import com.example.openweather.ui.weather.view.fragment.WeatherDetailsFragment

class FragmentNavigatorImpl(
    private val activity: FragmentActivity
) : FragmentNavigator {
    override fun showWeatherDetails() {
        activity.showFragment(
            R.id.fragmentContainer,
            WeatherDetailsFragment.newInstance(),
            true
        )
    }

}