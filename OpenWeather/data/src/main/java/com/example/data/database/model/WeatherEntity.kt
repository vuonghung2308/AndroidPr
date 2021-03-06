package com.example.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.common.mapper.DomainMapper
import com.example.data.database.TABLE_WEATHER
import com.example.data.networking.model.MainInfor
import com.example.data.networking.model.Weather
import com.example.domain.model.WeatherInfor

@Entity(tableName = TABLE_WEATHER)
data class WeatherEntity(
    @PrimaryKey
    val id: Int? = 0,
    val weather: List<Weather>?,
    @Embedded
    val main: MainInfor?,
    val name: String?
) : DomainMapper<WeatherInfor> {
    override fun mapToDomainEntity(): WeatherInfor {
        return WeatherInfor(
            main?.temp ?: 0.0,
            main?.pressure ?: 0.0,
            main?.humidity ?: 0
        )
    }
}