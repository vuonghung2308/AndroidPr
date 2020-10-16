package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.app.ShareCompat

class MainActivity : AppCompatActivity() {
    companion object {
        val LOG_TAG = MainActivity::class.java.simpleName
    }
    lateinit var mLocationEditText: EditText
    lateinit var mWebsiteEditText: EditText
    lateinit var mShareEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLocationEditText = findViewById(R.id.editText_loc)
        mShareEditText = findViewById(R.id.editText_share)
        mWebsiteEditText = findViewById(R.id.editText_uri)
    }

    fun shareText(view: View) {
        val txt = mShareEditText.text.toString()
        val mineType = "text/plain"
        ShareCompat.IntentBuilder.from(this)
            .setType(mineType)
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser()
    }
    fun openLocation(view: View) {
        val loc = mLocationEditText.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=" + loc)
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d(LOG_TAG, "Can't handle open location intent")
        }
    }
    fun openWebsite(view: View) {
        val uri = mWebsiteEditText.text.toString()
        val webpage = Uri.parse(uri)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d(LOG_TAG, "Can't handle open website intent")
        }
    }
}