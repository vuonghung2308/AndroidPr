package com.example.droidcafe

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object {
        val EXTRA_TYPE = MainActivity::class.qualifiedName + ".extra.TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun disPlayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun orderDonut(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_TYPE, "donut")
        startActivity(intent)
    }

    fun orderFroyo(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_TYPE, "froyo")
        startActivity(intent)
    }

    fun orderIcecream(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_TYPE, "icecream")
        startActivity(intent)
    }
}