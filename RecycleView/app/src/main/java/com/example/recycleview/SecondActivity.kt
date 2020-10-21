package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.recycleview.flick.FlickApi
import com.example.recycleview.data.Location
import com.example.recycleview.adapter.LocationAdapter
import com.example.recycleview.data.Page
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SecondActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {


    lateinit var name: String
    lateinit var lagitude: String
    lateinit var longitude: String
    lateinit var rvLocation: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var locationAdapter: LocationAdapter

    var list = ArrayList<Location>()
    val response by lazy {
        Retrofit.Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        name = intent.getStringExtra(MainActivity.EXTRA_TEXTNAME)!!
        lagitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLAGITUDE)!!
        longitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLONGITUDE)!!
        rvLocation = findViewById(R.id.rvLocation)
        locationAdapter = LocationAdapter(this)
        locationAdapter.list = list
        rvLocation.adapter = locationAdapter
        rvLocation.layoutManager = LinearLayoutManager(parent)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener(this)
        callApi(lagitude, longitude, { loadRecycleView(it) })
        Log.d("list", list.size.toString())
    }

    fun callApi(lat: String, lon: String, call: (res: Page) -> Unit) {
        val resl = response.getPhotos(
            MainActivity.METHOD,
            MainActivity.API_KEY,
            lat,
            lon,
            "json",
            "1"
        ).enqueue(object : Callback<Page> {
            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                val res = response.body() as Page
                call(res)
            }

            override fun onFailure(call: Call<Page>, t: Throwable) {
                Log.d("onFailure", t.toString())
            }
        })
    }

//    fun addToList(res: Page): List<Location> {
//        var list = ArrayList<Location>()
//        for (photo in res.photos.photo)
//            list.add(Location(name, lagitude, longitude, photo.getLink()))
//        return list
//    }

    fun loadRecycleView(res: Page) {
        for (i in 0..10) {
            list.add(Location(name, lagitude, longitude, res.photos.photo[i].getLink()))
        }
        locationAdapter.reloadView(list)
    }

    override fun onRefresh() {
//        Handler(Looper.myLooper()!!).postDelayed(object : Runnable {
//            override fun run() {
//                callApi(lagitude, longitude, { loadRecycleView(it) })
//            }
//        }, 2500)
    }

}