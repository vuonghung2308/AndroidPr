package com.example.githubapi.network

import com.example.githubapi.model.Profile
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApi {
    @GET("users/vuonghung2308")
    fun getProfile(): Call<Profile>
}