package com.example.implicitintentsreceiver

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val uri:Uri? = intent.data
        if(uri != null) {
            val uri_string = "URI: " + uri.toString()
            textView = findViewById(R.id.text_uri_message)
            textView.setText(uri_string)
        }
    }
}