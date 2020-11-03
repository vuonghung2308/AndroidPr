package com.example.recycleview

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.dialog.RemoveMarkerDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.abs


class MainActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnInfoWindowClickListener, GoogleMap.OnInfoWindowLongClickListener {

    companion object {
        val EXTRA_TEXTNAME = MainActivity::class.java.name + "extra.TEXTNAME"
        val EXTRA_TEXTLAT = MainActivity::class.java.name + "extra.TEXTLAT"
        val EXTRA_TEXTLON = MainActivity::class.java.name + "extra.TEXTLON"
    }

    private lateinit var map: GoogleMap
    lateinit var fragment: SupportMapFragment
    private val geocoder by lazy { Geocoder(this) }
    private val listMarker: ArrayList<Marker> by lazy { ArrayList() }
    private val home: LatLng by lazy { LatLng(40.7580033, -73.9866631) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = supportFragmentManager.findFragmentById(R.id.fragmet_map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(gmap: GoogleMap) {
        map = gmap
        map.setOnInfoWindowClickListener(this)
        map.setOnInfoWindowLongClickListener(this)
        map.setOnMapClickListener { latLng -> addMarker(latLng) }
        map.moveCamera(CameraUpdateFactory.newLatLng(home))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(home, 5f))
        addMarker(home)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "This place has no picture!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAddress(latLng: LatLng): String {
        return geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)[0]
            .getAddressLine(0)
    }

    override fun onInfoWindowClick(m: Marker) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.apply {
            putExtra(EXTRA_TEXTNAME, getAddress(m.position))
            putExtra(EXTRA_TEXTLAT, m.position.latitude.toString())
            putExtra(EXTRA_TEXTLON, m.position.longitude.toString())
        }
        startActivityForResult(intent, 1)
    }

    fun addMarker(latLng: LatLng) {
        val marker = map.addMarker(
            MarkerOptions()
                .title(getAddress(latLng))
                .position(latLng)
        )
        listMarker.add(marker)
        listMarker.last().showInfoWindow()
    }

    override fun onInfoWindowLongClick(marker: Marker) {
        val removeMarkerDialog = RemoveMarkerDialog(this)
        removeMarkerDialog.listenner = object : RemoveMarkerDialog.Listenner {
            override fun yesAction() {
                removeMarkerDialog.dismiss()
                marker.remove()
            }
        }
        removeMarkerDialog.show()
    }
}