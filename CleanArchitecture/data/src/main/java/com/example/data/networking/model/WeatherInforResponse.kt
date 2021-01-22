package com.example.data.networking.model

import com.example.data.database.model.WeatherEntity
import com.example.data.networking.base.DomainMapper
import com.example.data.networking.base.RoomMapper
import com.example.domain.model.WeatherInfor

data class WeatherInforResponse(
    val id: Int? = 0,
    val weather: List<Weather>?,
    val main: MainInfor?,
    val name: String?
) : RoomMapper<WeatherEntity>, DomainMapper<WeatherInfor> {

    override fun mapToRoomEntity(): WeatherEntity {
        return WeatherEntity(
            id,
            weather ?: arrayListOf(),
            main ?: MainInfor(),
            name ?: ""
        )
    }

    override fun mapToDomainEntity(): WeatherInfor {
        return WeatherInfor(
            main?.temp ?: 0.0,
            main?.humidity ?: 0,
            main?.pressure ?: 0.0
        )
    }

}

data class MainInfor(
    val temp: Double? = 0.0,
    val pressure: Double? = 0.0,
    val humidity: Int? = 0
)

data class Weather(
    val id: Int? = 0,
    val main: String? = "",
    val description: String? = "",
    val icon: String? = ""
)