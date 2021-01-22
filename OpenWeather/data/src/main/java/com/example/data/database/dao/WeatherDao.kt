package com.example.data.database.dao

import androidx.room.*
import com.example.data.database.TABLE_WEATHER
import com.example.data.database.model.WeatherEntity

@Dao
interface WeatherDao {
    @Transaction
    fun saveAndReturnWeatherEntity(weather: WeatherEntity): WeatherEntity {
        saveWeatherEntity(weather)
        return getWeatherForLocation(weather.name ?: "")
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeatherEntity(vararg weathers: WeatherEntity)

    @Query("SELECT * FROM $TABLE_WEATHER WHERE name = :city LIMIT 1")
    fun getWeatherForLocation(city: String): WeatherEntity
}