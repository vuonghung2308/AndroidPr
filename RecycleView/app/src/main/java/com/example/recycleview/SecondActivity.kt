package com.example.recycleview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.recycleview.adapter.LocationAdapter
import com.example.recycleview.data.Location
import com.example.recycleview.data.PlacePhotos
import com.example.recycleview.flickr.FilckrCall

class SecondActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener
/*, LocationAdapter.Listenner sử dụng button trong LocationAdapter để load more*/ {

    private val name by lazy { intent.getStringExtra(MainActivity.EXTRA_TEXTNAME).toString() }
    private val lat by lazy { intent.getStringExtra(MainActivity.EXTRA_TEXTLAT).toString() }
    private val lon by lazy { intent.getStringExtra(MainActivity.EXTRA_TEXTLON).toString() }
    private val locationAdapter by lazy { LocationAdapter() }
    private var count = 0
    private var isLoading = false
    private lateinit var placePhotos: PlacePhotos
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    inner class scrollEnd : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val total = layoutManager.itemCount
            val lastVisible = layoutManager.findLastVisibleItemPosition()
            if (!isLoading && lastVisible == total - 1) {
                Log.d("la", lastVisible.toString() + " " + total.toString())
                isLoading = true
                locationAdapter.isLoading = true;
                Handler(Looper.myLooper()!!).postDelayed({
                    loadMore()
                    isLoading = false
                    locationAdapter.isLoading = false;
                }, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        recyclerView = findViewById(R.id.rvLocation)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        recyclerView.addOnScrollListener(scrollEnd())
        swipeRefreshLayout.setOnRefreshListener(this)
//        locationAdapter.listenner = this  đặt sự kiện cho button (sử dụng button để load thêm ảnh)
        recyclerView.adapter = locationAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        FilckrCall.callApi(lat, lon) {
            placePhotos = it
            loadRecycleView(placePhotos)
        }
    }

    fun loadRecycleView(placePhotos: PlacePhotos, isReload: Boolean = true, num: Int = 20) {
        val list = ArrayList<Location>()

        if (placePhotos.photos.photo.isEmpty()) {
            setResult(RESULT_OK)
            finish()
        }
        if (placePhotos.photos.photo.size < count + num) {
            for (i in count..placePhotos.photos.photo.size - 1) {
                list.add(Location(name, lat, lon, placePhotos.photos.photo[i].getLink()))
            }
        } else {
            for (i in count..count + 19)
                list.add(Location(name, lat, lon, placePhotos.photos.photo[i].getLink()))
        }
        if (isReload)
            locationAdapter.reloadView(list)
        else locationAdapter.addView(list)
    }

    override fun onRefresh() {
        count = 0
        FilckrCall.callApi(lat, lon) {
            placePhotos = it
            loadRecycleView(placePhotos)
        }
        swipeRefreshLayout.isRefreshing = false
    }

    fun loadMore() {
        val con = placePhotos.photos.photo.size - (count + 20)
        if (con >= 20) {
            count += 20
            loadRecycleView(placePhotos, false)
        } else if (con <= 0) {
            Toast.makeText(this, "No more picture!", Toast.LENGTH_SHORT).show()
        } else {
            count += con;
            loadRecycleView(placePhotos, false, con)
        }
    }
//      thiết lập sự kiện cho button load thêm ảnh
//    override fun onButtonClick() {
//        val con = placePhotos.photos.photo.size - (count + 20)
//        if (con >= 20) {
//            count += 20
//            loadRecycleView(placePhotos, false)
//        } else if (con <= 0) {
//            Toast.makeText(this, "No more picture!", Toast.LENGTH_SHORT).show()
//        } else {
//            count += con;
//            loadRecycleView(placePhotos, false, con)
//        }
//    }
}