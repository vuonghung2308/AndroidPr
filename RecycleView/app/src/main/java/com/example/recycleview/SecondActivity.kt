package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyleview.FlickApiService
import com.example.recyleview.Location
import com.example.recyleview.LocationAdapter
import com.example.recyleview.Respone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val name = intent.getStringExtra(MainActivity.EXTRA_TEXTNAME)
        val lagitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLAGITUDE)
        val longitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLONGITUDE)
        val rvLocation = findViewById<RecyclerView>(R.id.rvLocation)
        val list = ArrayList<Location>()
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val respone = retrofit.create(FlickApiService::class.java)
        val call = respone.getPhotos(
            MainActivity.METHOD,
            MainActivity.API_KEY,
            lagitude!!,
            longitude!!,
            "json",
            "1"
        )
        call.enqueue(object : Callback<Respone> {
            override fun onResponse(call: Call<Respone>, response: Response<Respone>) {
                val res = response.body() as Respone
                for (i in 0..10)
                    list.add(Location(name, lagitude, longitude, res.photos.photo[i].getLink()))
                val locationAdapter = LocationAdapter(list)
                Log.d("list", list.toString())
                rvLocation.adapter = locationAdapter
                rvLocation.layoutManager = LinearLayoutManager(parent)
            }

            override fun onFailure(call: Call<Respone>, t: Throwable) {
                Log.d("onFailure", t.toString())
            }
        })
        Log.d("outSide", list.toString())
    }
}