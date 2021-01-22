package com.example.data.common.utils

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityImpl(
    private val context: Context
) : Connectivity {
    @Suppress("DEPRECATION")
    override fun hasNetworkAccess(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val infor = connectivityManager.activeNetworkInfo
        return infor != null && infor.isConnected
    }
}