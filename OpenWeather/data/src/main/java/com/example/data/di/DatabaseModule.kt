package com.example.data.di

import androidx.room.Room
import com.example.data.database.DB_WEATHER
import com.example.data.database.WeatherDatabase
import com.example.data.database.dao.WeatherDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherDatabase::class.java,
            DB_WEATHER
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<WeatherDatabase>().weatherDao() }
}