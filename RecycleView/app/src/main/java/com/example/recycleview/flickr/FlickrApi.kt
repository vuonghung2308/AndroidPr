package com.example.recycleview.flickr

import com.example.recycleview.data.PlacePhotos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("/services/rest/")
    fun getPlacePhotos(
        @Query("method") method: String?,
        @Query("api_key") api_key: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
//        @Query("page") page: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: String
    ): Call<PlacePhotos>
}