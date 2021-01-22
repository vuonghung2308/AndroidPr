package com.example.data.networking.model

import com.example.data.common.mapper.RoomMapper
import com.example.data.database.model.WeatherEntity

data class WeatherResponse(
    val id: Int? = 0,
    val weather: List<Weather>?,
    val main: MainInfor?,
    val name: String?
) : RoomMapper<WeatherEntity> {
    override fun mapToRoomEntity(): WeatherEntity {
        return WeatherEntity(
            id ?: 0,
            weather ?: arrayListOf(),
            main ?: MainInfor(),
            name ?: ""
        )
    }
}

data class Weather(
    val id: Int? = 0,
    val main: String? = "",
    val description: String? = "",
    val icon: String? = ""
)

data class MainInfor(
    val temp: Double? = 0.0,
    val pressure: Double? = 0.0,
    val humidity: Int? = 0
)