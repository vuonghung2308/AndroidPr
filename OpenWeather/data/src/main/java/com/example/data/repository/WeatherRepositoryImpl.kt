package com.example.data.repository

import com.example.data.database.dao.WeatherDao
import com.example.data.database.model.WeatherEntity
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.getData
import com.example.domain.model.Result
import com.example.domain.model.WeatherInfor
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi
) : BaseRepository<WeatherInfor, WeatherEntity>(), WeatherRepository {
    override suspend fun getWeatherForLocation(city: String): Result<WeatherInfor> {
        return fetchData(
            apiDataProvider = {
                weatherApi.getWeatherForLocation(city).getData(
                    cacheAction = { weatherDao.saveWeatherEntity(it) },
                    fetchFromCache = { weatherDao.getWeatherForLocation(city) }
                )
            },
            dbDataProvider = { weatherDao.getWeatherForLocation(city) }
        )
    }
}