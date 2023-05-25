package com.khaledamin.mawsoaa.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun isInternetConnected(context: Context): Boolean {
    val connectivityManager = context.applicationContext
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork
        if (network != null) {
            val capabilities = connectivityManager.getNetworkCapabilities(network)
             capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                     || capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                     || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
           false
        }
    } else {
        val activeInfo = connectivityManager.activeNetworkInfo
        activeInfo != null && activeInfo.isConnected
    }
}

