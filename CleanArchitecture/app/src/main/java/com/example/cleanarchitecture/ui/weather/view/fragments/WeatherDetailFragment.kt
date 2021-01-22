package com.example.cleanarchitecture.ui.weather.view.fragments

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.base.BaseFragment

class WeatherDetailFragment : BaseFragment() {

    companion object {
        fun newInstance(): WeatherDetailFragment = WeatherDetailFragment()
    }

    override fun getLayout(): Int = R.layout.fragment_weather_detail

    override fun viewReady() {}
}