package com.example.recycleview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        val EXTRA_TEXTNAME = MainActivity::class.java.name + "extra.TEXTNAME"
        val EXTRA_TEXTLAT = MainActivity::class.java.name + "extra.TEXTLAT"
        val EXTRA_TEXTLON = MainActivity::class.java.name + "extra.TEXTLON"
    }

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment =
            supportFragmentManager.findFragmentById(R.id.fragmet_map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(gmap: GoogleMap) {
        map = gmap
        val timesquare = LatLng(40.7580033, -73.9866631)
        map.addMarker(MarkerOptions().title("Marker in Times Square").position(timesquare))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(timesquare, 4f))
        map.setOnMapClickListener { latLng ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.apply {
                putExtra(EXTRA_TEXTNAME, "Times Square")
                putExtra(EXTRA_TEXTLAT, latLng.latitude.toString())
                putExtra(EXTRA_TEXTLON, latLng.longitude.toString())
            }
            startActivityForResult(intent, 1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "This place has no picture!", Toast.LENGTH_SHORT).show()
        }
    }
}