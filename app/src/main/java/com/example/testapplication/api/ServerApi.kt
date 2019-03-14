package com.example.testapplication.api

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.testapplication.app.AppLoader
import com.example.testapplication.broadcast.ConnectionBroadcastReceiver
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerApi {
    private var retrofit: Retrofit? = null
    private const val baseurl = "http://192.168.43.191:3000"

    fun health(activity: Activity): Retrofit? {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cacheDir = Cache(activity.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder().cache(cacheDir).addInterceptor { chain ->
            var request = chain.request()

            request = if (ConnectionBroadcastReceiver.networkState)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            chain.proceed(request)
        }.build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }

}