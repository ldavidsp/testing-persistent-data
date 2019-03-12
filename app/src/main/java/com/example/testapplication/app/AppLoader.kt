package com.example.testapplication.app

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

class AppLoader: Application() {
    override fun onCreate() {
        super.onCreate()
        registerReceiver(networkStateReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onTerminate() {
        unregisterReceiver(networkStateReceiver)
        super.onTerminate()
    }

    private val networkStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = manager.activeNetworkInfo
            onNetworkChange(ni)
        }
    }

    private fun onNetworkChange(networkInfo: NetworkInfo?) {
        if (networkInfo != null) {
            if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                Log.d("MainActivity", "CONNECTED")
            } else {
                Log.d("MenuActivity", "DISCONNECTED")
            }
        }
    }
}