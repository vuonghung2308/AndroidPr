package com.example.openweather.routing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.openweather.ui.ScreenType
import com.example.openweather.ui.weather.view.activity.WeatherActivity

class ActivityNavigatorImpl(
    private val activity: AppCompatActivity
) : ActivityNavigator {
    override fun showWeatherActivity() {
        navigateTo(getIntent<WeatherActivity>().apply {
            putExtra(SCREEN_TYPE, ScreenType.WEATHER)
        })
    }

    private fun navigateTo(intent: Intent) {
        activity.startActivity(intent)
    }

    private inline fun <reified T : Any> getIntent(): Intent {
        return Intent(activity, T::class.java)
    }

    companion object {
        const val SCREEN_TYPE = "screen_type"
    }
}