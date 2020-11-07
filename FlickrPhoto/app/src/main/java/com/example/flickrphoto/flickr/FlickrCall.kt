package com.example.flickrphoto.flickr

import android.util.Log
import com.example.flickrphoto.flickr.data.PlacePhoto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FlickrCall {
    private const val BASE_URL = "https://www.flickr.com/"
    private const val API_KEY = "0dcb9038c8a2f724da6ee4ce6ec89a8e"
    private const val METHOD = "flickr.photos.search"
    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun callApi(lat: Double, lon: Double, call: (res: PlacePhoto) -> Unit) {
        retrofit.create(FilckrApi::class.java)
            .getPhoto(METHOD, API_KEY, lat, lon)
            .enqueue(object :
                Callback<PlacePhoto> {
                override fun onResponse(
                    call: Call<PlacePhoto>,
                    response: Response<PlacePhoto>
                ) {
                    val res = response.body() as PlacePhoto
                    call(res)
                }

                override fun onFailure(call: Call<PlacePhoto>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }

            })
    }
}