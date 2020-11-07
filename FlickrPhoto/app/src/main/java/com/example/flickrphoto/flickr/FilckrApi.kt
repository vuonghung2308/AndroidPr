package com.example.flickrphoto.flickr

import com.example.flickrphoto.flickr.data.PlacePhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilckrApi {
    @GET("/services/rest")
    fun getPhoto(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") callback: Int = 1
    ): Call<PlacePhoto>
}