package com.example.data.database.dao

import androidx.room.*
import com.example.data.database.WEATHER_TABLE_NAME
import com.example.data.database.model.WeatherEntity

@Dao
interface WeatherDao {

    @Transaction
    suspend fun udateWeatherAndReturn(weather: WeatherEntity): WeatherEntity {
        saveWeatherInfor(weather)
        return getWeatherInforForCity(weather.name ?: "")
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherInfor(weather: WeatherEntity)

    @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE name = :city LIMIT 1")
    suspend fun getWeatherInforForCity(city: String): WeatherEntity
}