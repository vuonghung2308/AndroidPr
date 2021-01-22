package com.example.data.di

import com.example.data.networking.BASE_URL
import com.example.data.networking.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkingModule = module {
    single<Converter.Factory> { GsonConverterFactory.create() }
    single<Interceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get())
            .callTimeout(10, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(get())
            .client(get())
            .baseUrl(BASE_URL)
            .build()
    }
    single { get<Retrofit>().create(WeatherApi::class.java) }
}