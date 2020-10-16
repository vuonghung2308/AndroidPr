package com.example.recyleview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickApiService {
    @GET("services/rest/")
    fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") key: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: String
    ): Call<Respone>
}