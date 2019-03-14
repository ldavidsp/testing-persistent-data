package com.example.testapplication.app

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.testapplication.broadcast.ConnectionBroadcastReceiver

class AppLoader: Application() {

    override fun onCreate() {
        super.onCreate()
        registerReceiver(ConnectionBroadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onTerminate() {
        unregisterReceiver(ConnectionBroadcastReceiver)
        super.onTerminate()
    }
}