package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.networking.model.MainInfor
import com.example.data.networking.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson: Gson by lazy { Gson() }

    @TypeConverter
    fun fromWeatherListToJson(list: List<Weather>?): String {
        return list?.let { gson.toJson(list) } ?: ""
    }

    @TypeConverter
    fun fromJsonToWeatherList(json: String?): List<Weather> {
        val typeConverter = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(json, typeConverter)
    }

    @TypeConverter
    fun fromMainInforToJson(main: MainInfor?): String {
        return main?.let { gson.toJson(main) } ?: ""
    }

    @TypeConverter
    fun fromJsonToMainInfor(json: String?): MainInfor {
        val typeConverter = object : TypeToken<MainInfor>() {}.type
        return gson.fromJson(json, typeConverter)
    }
}