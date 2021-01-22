package com.example.domain.interaction

import com.example.domain.base.BaseUseCase
import com.example.domain.model.Result
import com.example.domain.model.WeatherInfor

interface GetWeatherUseCase: BaseUseCase<String, WeatherInfor> {
    override suspend fun invoke(param: String): Result<WeatherInfor>
}