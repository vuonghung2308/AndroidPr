package com.example.flickrphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.flickrphoto.adapter.ItemAdapter
import com.example.flickrphoto.database.AppDatabase
import com.example.flickrphoto.entity.Link
import com.example.flickrphoto.entity.Point
import com.example.flickrphoto.flickr.FlickrCall
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val lat by lazy { intent.getDoubleExtra(MainActivity.EXTRA_LAT, 0.0) }
    private val lon by lazy { intent.getDoubleExtra(MainActivity.EXTRA_LON, 0.0) }
    private lateinit var point: Point
    private lateinit var links: ArrayList<String>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var count = 0

    inner class scrollEnd : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val total = layoutManager.itemCount
            val last = layoutManager.findLastVisibleItemPosition()
            if (adapter.isLoading == false && last == total - 1) {
                recyclerView.post { adapter.isLoading = true }
                Handler(Looper.myLooper()!!).postDelayed(
                    {
                        adapter.isLoading = false
                        addToRecycleView()
                    },
                    1000
                )

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initComponent()
        initView()
    }

    fun initComponent() {
        recyclerView = findViewById(R.id.recycler_view)
        swipeRefreshLayout = findViewById(R.id.refresh_layout)
        adapter = ItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        swipeRefreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(scrollEnd())
    }

    private fun initView() {
        adapter.isLoading = true
        loadFromDatabase()
        if (links.isEmpty())
            loadFromFlickr()
        else {
            reloadRecycleView()
            adapter.isLoading = false
        }
    }

    private fun loadFromFlickr() {
        FlickrCall.callApi(lat, lon) {
            links = it.getLinks() as ArrayList<String>
            if (links.isEmpty()) {
                setResult(RESULT_OK)
                finish()
            } else {
                saveToDatabase()
                reloadRecycleView()
                adapter.isLoading = false
            }
        }
    }

    private fun loadFromDatabase() {
        point = AppDatabase.getInstance(applicationContext).pointDao().getPoint(lat, lon)
        links = AppDatabase.getInstance(applicationContext).linkDao().getLinks(point.id)
                as ArrayList<String>
    }

    private fun reloadRecycleView(num: Int = 10) {
        count = 0
        if (links.size < num) {
            adapter.reloadView(links, point.address)
            count = links.size
        } else {
            adapter.reloadView(links.subList(count, count + num - 1), point.address)
            count = count + num
        }
    }

    fun addToRecycleView(num: Int = 10) {
        Log.d("load", "load")
        if (links.size == count) {
            Toast.makeText(this, "No more picture!", Toast.LENGTH_SHORT).show()
        } else if (links.size < num + count) {
            adapter.addView(links.subList(count, links.size - 1), point.address)
            count = links.size
        } else {
            adapter.addView(links.subList(count, count + num - 1), point.address)
            count = count + num
        }
    }

    override fun onRefresh() {
        reloadRecycleView()
        swipeRefreshLayout.isRefreshing = false
    }

    fun saveToDatabase() {
        GlobalScope.launch {
            val list = ArrayList<Link>()
            for (link in links) {
                list.add(Link(point.id, link))
            }
            AppDatabase.getInstance(applicationContext).linkDao()
                .insertLink(list)
        }
    }
}