package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.ui.weather.presentation.WeatherViewModel
import com.example.cleanarchitecture.ui.welcome.presentation.WelcomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { WeatherViewModel(get()) }
    viewModel { WelcomeViewModel() }
}