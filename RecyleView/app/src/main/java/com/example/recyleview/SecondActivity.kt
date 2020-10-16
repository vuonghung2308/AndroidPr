package com.example.recyleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val name = intent.getStringExtra(MainActivity.EXTRA_TEXTNAME)
        val lagitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLAGITUDE)
        val longitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLONGITUDE)
        val list = ArrayList<Location>()
        list.add(Location(name, lagitude, longitude, "https://pix10.agoda.net/hotelImages" +
                "/5076278/0/7ec23fba522007933686b1e138a05ae1.jpg"))
        list.add(Location(name, lagitude, longitude, "https://cdn.abcotvs.com/dip/images/" +
                "5803793_123119-nyeballdrop.jpg"))
        list.add(Location(name, lagitude, longitude, "https://media.wired.com/photos/5926" +
                "5c018d4ebc5ab8069fd2/master/pass/TS-HP-201001_NY_Times_Square_Sn_hetta_N58.jpg"))
        list.add(Location(name, lagitude, longitude, "https://bostonglobe-prod.cdn.arcpub" +
                "lishing.com/resizer/Ufk5vzJuo1WC8z_gmW47PMXBN54=/1440x0/arc-anglerfish-arc2-p" +
                "rod-bostonglobe.s3.amazonaws.com/public/5A3S3VJARJBL3GZSWGN5ZI467M.jpg"))
        list.add(Location(name, lagitude, longitude, "https://media.nbcnewyork.com/2019/0" +
                "9/GettyImages-1218580816-e1592998526508.jpg"))
        list.add(Location(name, lagitude, longitude, "https://cf.bstatic.com/images/hotel" +
                "/max1024x768/128/128439923.jpg"))
        list.add(Location(name, lagitude, longitude, "https://pix10.agoda.net/hotelImages" +
                "/7833/0/864c4d6eeb94c2cddc3c6dba23af36ac.jpg?s=1024x768"))
        list.add(Location(name, lagitude, longitude, "https://cdn.vox-cdn.com/thumbor/pJN" +
                "Au3BqjuI1gThp4iH1AFWoaP0=/0x0:3000x2247/1200x800/filters:focal(1260x884:1740x" +
                "1364)/cdn.vox-cdn.com/uploads/chorus_image/image/57788013/timessquare.0.jpg"))
        list.add(Location(name, lagitude, longitude, "https://dynaimage.cdn.cnn.com/cnn/c" +
                "_fill,g_auto,w_1200,h_675,ar_16:9/https%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2" +
                "Fassets%2F200818041331-01-krispy-kreme-times-square.jpg"))
        list.add(Location(name, lagitude, longitude, "https://image.newyork.com.au/wp-con" +
                "tent/uploads/2012/09/Times-Square-in-New-York-Billboards.jpg"))
        list.add(Location(name, lagitude, longitude, "https://offloadmedia.feverup.com/se" +
                "cretnyc.co/wp-content/uploads/2018/12/10122353/shutterstock_1418089289-2.jpg"))
        val locationAdapter = LocationAdapter(list)
        val rvLocation = findViewById<RecyclerView>(R.id.rvLocation)
        rvLocation.adapter = locationAdapter
        rvLocation.layoutManager = LinearLayoutManager(this)
    }
}