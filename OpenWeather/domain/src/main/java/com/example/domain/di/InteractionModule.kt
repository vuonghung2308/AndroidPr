package com.example.domain.di

import com.example.domain.interaction.GetWeatherUseCase
import com.example.domain.interaction.GetWeatherUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
    factory<GetWeatherUseCase> { GetWeatherUseCaseImpl(get()) }
}