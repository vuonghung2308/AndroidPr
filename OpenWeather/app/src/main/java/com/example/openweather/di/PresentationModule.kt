package com.example.openweather.di

import com.example.openweather.ui.weather.presentation.WeatherViewModel
import com.example.openweather.ui.welcome.presentation.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { WeatherViewModel(get()) }
    viewModel { WelcomeViewModel() }
}