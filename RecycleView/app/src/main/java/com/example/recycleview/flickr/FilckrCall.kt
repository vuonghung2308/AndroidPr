package com.example.recycleview.flickr

import android.util.Log
import com.example.recycleview.data.PlacePhotos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FilckrCall {
    val BASE_URL: String = "https://www.flickr.com/"
    val METHOD: String = "flickr.photos.search"
    val API_KEY: String = "0dcb9038c8a2f724da6ee4ce6ec89a8e"
    val FORMAT: String = "json"
    val CALL_BACK: String = "1"

    private val response by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickrApi::class.java)
    }

    fun callApi(lat: String, lon: String, call: (res: PlacePhotos) -> Unit) {
        response.getPlacePhotos(METHOD, API_KEY, lat, lon, FORMAT, CALL_BACK)
            .enqueue(object : Callback<PlacePhotos> {
                override fun onResponse(call: Call<PlacePhotos>, response: Response<PlacePhotos>) {
                    val res = response.body() as PlacePhotos
                    call(res)
                }

                override fun onFailure(call: Call<PlacePhotos>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }

            })
    }
}