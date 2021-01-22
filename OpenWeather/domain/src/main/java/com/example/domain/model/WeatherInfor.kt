package com.example.domain.model

data class WeatherInfor(
        val temperature: Double,
        val pressure: Double,
        val humidity: Int,
) {
    fun convertKelvinToCelsius() = temperature - 272.15
}