package com.example.githubapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.githubapi.App
import com.example.githubapi.R
import com.example.githubapi.model.Profile
import com.example.githubapi.network.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var api: GitHubApi
    val texResult: TextView by lazy { findViewById(R.id.texResult) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.get().component.inject(this)
        api.getProfile().enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                texResult.text = response.body()?.login
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.localizedMessage)
            }
        })
    }
}