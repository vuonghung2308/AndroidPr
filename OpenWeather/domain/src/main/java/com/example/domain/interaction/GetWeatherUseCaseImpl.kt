package com.example.domain.interaction

import com.example.domain.model.Result
import com.example.domain.model.WeatherInfor
import com.example.domain.repository.WeatherRepository

class GetWeatherUseCaseImpl(
        private val weatherRepository: WeatherRepository
) : GetWeatherUseCase {
    override suspend fun invoke(param: String): Result<WeatherInfor> {
        return weatherRepository.getWeatherForLocation(param)
    }
}