package com.example.testapplication.app

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.example.testapplication.broadcast.ConnectionBroadcastReceiver
import com.dbflow5.database.AndroidSQLiteOpenHelper
import com.dbflow5.config.DatabaseConfig
import com.dbflow5.config.FlowConfig
import com.dbflow5.config.FlowManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.Builder(applicationContext)
            .database(DatabaseConfig.builder(PrepackagedDB::class.java) { dbFlowDatabase, databaseHelperListener ->
                AndroidSQLiteOpenHelper(
                    this@MyApplication,
                    dbFlowDatabase,
                    databaseHelperListener
                )
            }.databaseName("prepackaged").build())
            .build())

        registerReceiver(ConnectionBroadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onTerminate() {
        FlowManager.destroy()
        unregisterReceiver(ConnectionBroadcastReceiver)
        super.onTerminate()
    }
}