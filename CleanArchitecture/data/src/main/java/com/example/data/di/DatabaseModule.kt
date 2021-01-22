package com.example.data.di

import androidx.room.Room
import com.example.data.database.WeatherDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val WEARTHER_DB = "weather_database"

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), WeatherDatabase::class.java, WEARTHER_DB)
            .fallbackToDestructiveMigration().build()
    }
    factory { get<WeatherDatabase>().weatherDao() }
}