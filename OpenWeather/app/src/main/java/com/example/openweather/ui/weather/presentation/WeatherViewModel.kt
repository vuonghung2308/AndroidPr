package com.example.openweather.ui.weather.presentation

import android.util.Log
import com.example.domain.interaction.GetWeatherUseCase
import com.example.domain.model.WeatherInfor
import com.example.openweather.ui.WeatherViewEffect
import com.example.openweather.ui.base.BaseViewModel
import com.example.openweather.ui.base.Error
import com.example.openweather.ui.base.Success

class WeatherViewModel(
    private val getWeather: GetWeatherUseCase
) : BaseViewModel<WeatherInfor, WeatherViewEffect>() {
    fun getWeatherForLocation(city: String) {
        executeUseCase {
            getWeather(city)
                .onSuccess { _viewState.value = Success(it) }
                .onFailure { _viewState.value = Error(it.t) }
        }
    }
}