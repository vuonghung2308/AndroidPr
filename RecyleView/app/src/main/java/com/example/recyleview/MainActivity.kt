package com.example.recyleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    companion object {
        val EXTRA_TEXTNAME: String = MainActivity::class.java.name + ".extra.TEXTNAME"
        val EXTRA_TEXTLAGITUDE = MainActivity::class.java.name + ".extra.TEXTLAGITUDE"
        val EXTRA_TEXTLONGITUDE = MainActivity::class.java.name + ".extra.TEXTLONGITUDE"
    }

    lateinit var nameText: EditText
    lateinit var lagitudeText: EditText
    lateinit var longitudeText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameText = findViewById(R.id.text_name)
        lagitudeText = findViewById(R.id.text_lagitude)
        longitudeText = findViewById(R.id.text_longitude)
    }
    fun loadImage(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.apply {
            putExtra(EXTRA_TEXTNAME, nameText.text.toString())
            putExtra(EXTRA_TEXTLAGITUDE, lagitudeText.text.toString())
            putExtra(EXTRA_TEXTLONGITUDE, longitudeText.text.toString())
        }
        startActivity(intent)
    }
}