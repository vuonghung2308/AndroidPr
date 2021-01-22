package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.networking.model.MainInfor
import com.example.data.networking.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromWeatherListToJson(list: List<Weather>?): String {
        return list?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToWeatherList(jsonList: String): List<Weather> {
        val listType = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(jsonList, listType)
    }

// MainInfor converters

    @TypeConverter
    fun fromMainInforToJson(mainInfor: MainInfor?): String {
        return mainInfor?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToMainInfor(json: String): MainInfor {
        val type = object : TypeToken<MainInfor>() {}.type
        return gson.fromJson(json, type)
    }

}
