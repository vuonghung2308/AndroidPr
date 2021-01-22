package com.example.cleanarchitecture.routing

import androidx.fragment.app.FragmentActivity
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.common.extensions.showFragment
import com.example.cleanarchitecture.ui.weather.view.fragments.WeatherDetailFragment

class AppFragmentNavigator(private val activity: FragmentActivity) : FragmentNavigator {
    override fun showWeatherDetail() =
        activity.showFragment(WeatherDetailFragment.newInstance(), R.id.fragmentContainer, false)
}