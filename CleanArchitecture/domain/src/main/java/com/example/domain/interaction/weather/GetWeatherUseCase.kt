package com.example.domain.interaction.weather

import com.example.domain.base.BaseUseCase
import com.example.domain.model.Result
import com.example.domain.model.WeatherInfor

interface GetWeatherUseCase : BaseUseCase<String, WeatherInfor> {
    override suspend operator fun invoke(param: String): Result<WeatherInfor>
}