package com.example.openweather.ui.weather.view.fragment

import com.example.openweather.R
import com.example.openweather.ui.base.BaseFragment

class WeatherDetailsFragment : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.activity_weather_details
    }

    override fun onViewReady() {}

    companion object {
        fun newInstance() = WeatherDetailsFragment()
    }

}