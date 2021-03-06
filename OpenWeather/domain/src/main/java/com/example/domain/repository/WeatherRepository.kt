package com.example.domain.repository

import com.example.domain.model.Result
import com.example.domain.model.WeatherInfor

interface WeatherRepository {
    suspend fun getWeatherForLocation(city: String): Result<WeatherInfor>
}