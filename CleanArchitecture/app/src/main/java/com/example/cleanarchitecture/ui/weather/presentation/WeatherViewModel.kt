package com.example.cleanarchitecture.ui.weather.presentation

import com.example.cleanarchitecture.common.extensions.DEFAULT_CITY_NAME
import com.example.cleanarchitecture.ui.base.BaseViewModel
import com.example.cleanarchitecture.ui.base.Error
import com.example.cleanarchitecture.ui.base.Success
import com.example.cleanarchitecture.ui.weather.view.WeatherViewEffect
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.model.WeatherInfor
import com.example.domain.model.onFailure
import com.example.domain.model.onSuccess

class WeatherViewModel(private val getWeather: GetWeatherUseCase) :
    BaseViewModel<WeatherInfor, WeatherViewEffect>() {
    fun getWeatherForLocation(location: String = DEFAULT_CITY_NAME) = executeUseCase {
        getWeather(location)
            .onSuccess { _viewState.value = Success(it) }
            .onFailure { _viewState.value = Error(it.throwable) }
    }
}