package com.example.testapplication.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

object ConnectionBroadcastReceiver: BroadcastReceiver() {
    private var networkState: Boolean = false

    override fun onReceive(context: Context?, intent: Intent?) {
        val manager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        onNetworkChange(networkInfo)
    }

    private fun onNetworkChange(networkInfo: NetworkInfo?) {
        if (networkInfo != null && networkInfo.isConnected) {
            networkState = true
            Log.d("MainActivity", "CONNECTED")
        } else {
            networkState = false
            Log.d("MainActivity", "DISCONNECTED")
        }
    }


}

