package com.example.recycleview.flick

import com.example.recycleview.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlickCall {
    val retrofit = Retrofit.Builder()
        .baseUrl(MainActivity.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}