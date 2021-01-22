package com.example.openweather.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.openweather.routing.ActivityNavigator
import com.example.openweather.routing.ActivityNavigatorImpl
import com.example.openweather.routing.FragmentNavigator
import com.example.openweather.routing.FragmentNavigatorImpl
import org.koin.dsl.module

val appModule = module {
    single { CoroutineContextProvider() }
    single<ActivityNavigator> { (activity: AppCompatActivity) -> ActivityNavigatorImpl(activity) }
    single<FragmentNavigator> { (activity: FragmentActivity) -> FragmentNavigatorImpl(activity) }
}