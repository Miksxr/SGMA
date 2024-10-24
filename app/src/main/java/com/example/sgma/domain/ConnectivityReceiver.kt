package com.example.sgma.domain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest


class ConnectivityReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        checkInternetConnection(context!!)
    }

    companion object {
        var isInternetConnect : Boolean = false

        fun checkInternetConnection(context: Context) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            connectivityManager.registerNetworkCallback(request, object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isInternetConnect = true
                }

                override fun onUnavailable() {
                    isInternetConnect = false
                }
            })
        }
    }
}