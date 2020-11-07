package com.example.flickrphoto

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.flickrphoto.database.AppDatabase
import com.example.flickrphoto.entity.Point
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnInfoWindowLongClickListener, GoogleMap.OnInfoWindowClickListener {

    companion object {
        val EXTRA_LAT = MainActivity::class.java.name + ".extra.LAT"
        val EXTRA_LON = MainActivity::class.java.name + ".extra.LON"
    }

    private lateinit var fragment: SupportMapFragment
    private lateinit var map: GoogleMap
    private lateinit var listPoint: List<Point>
    private val home: LatLng by lazy { LatLng(40.757999, -73.985533) }
    private val geocoder: Geocoder by lazy { Geocoder(this) }
    private val listMarker by lazy { ArrayList<Marker>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
    }

    fun initComponent() {
        listPoint = AppDatabase.getInstance(applicationContext).pointDao().getAllPoint()
        fragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(gmap: GoogleMap) {
        map = gmap
        map.setOnMapClickListener(this)
        map.setOnInfoWindowClickListener(this)
        map.setOnInfoWindowLongClickListener(this)
        if (listPoint.isEmpty()) {
            listMarker.add(map.addMarker(MarkerOptions().position(home).title("Marker in Times Square")))
        } else showMarker()
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(listMarker.last().position, 5f))
        Handler(Looper.myLooper()!!).postDelayed(
            Runnable { listMarker.last().showInfoWindow() },
            1750
        )
    }

    override fun onMapClick(latLng: LatLng) {
        val point = Point(latLng.latitude, latLng.longitude, getAddress(latLng))
        AppDatabase.getInstance(applicationContext).pointDao().insertPoint(point)
        listMarker.add(map.addMarker(MarkerOptions().position(latLng).title(getAddress(latLng))))
        listMarker.last().showInfoWindow()
    }

    fun getAddress(latLng: LatLng): String {
        return geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)[0]
            .getAddressLine(0)
    }

    fun showMarker() {
        for (point in listPoint) {
            listMarker.add(
                map.addMarker(
                    MarkerOptions()
                        .title(point.address)
                        .position(LatLng(point.lat, point.lon))
                )
            )
        }
    }

    override fun onInfoWindowLongClick(marker: Marker) {
        val dialog = RemoveDialog(this)
        dialog.setListenner(object : RemoveDialog.Listenner {
            override fun yesAction() {
                AppDatabase.getInstance(applicationContext).pointDao()
                    .deletePoint(marker.position.latitude, marker.position.longitude)
                marker.remove()
                dialog.dismiss()
            }
        })
        dialog.show()
    }

    override fun onInfoWindowClick(marker: Marker) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.apply {
            putExtra(EXTRA_LAT, marker.position.latitude)
            putExtra(EXTRA_LON, marker.position.longitude)
        }
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK)
            Toast.makeText(this, "This place has no photo", Toast.LENGTH_SHORT).show()
    }
}