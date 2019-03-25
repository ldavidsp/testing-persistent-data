package com.example.testapplication.api

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object ServerApi {
    private lateinit var retrofit: Retrofit
    //private const val baseurl = "http://192.168.43.191" //Samsung
    //private const val baseurl = "http://192.168.200.27" //hardware
    private const val baseurl = "https://jsonplaceholder.typicode.com" //JSON TEST

    fun health(activity: Activity): Retrofit {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val cacheDir = Cache(activity.cacheDir, cacheSize)

        val client = OkHttpClient.Builder()
            .cache(cacheDir)
            .addNetworkInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                     if (!networkState(activity)) {
                         request = request.newBuilder()
                             .cacheControl(CacheControl.FORCE_CACHE)
                             .build()
                     }

                    val originalResponse = chain.proceed(request)
                    val cacheControl = originalResponse.header("Cache-Control")

                    return if (isRemoteNoCache(cacheControl)) {
                        originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + 5000)
                            .build()
                    } else
                        originalResponse
                }

            })
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    request = if (networkState(activity)) {
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                    } else {
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    }
                    return chain.proceed(request)
                }
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit
    }

    private fun networkState(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }

    private fun isRemoteNoCache(cacheControl: String?): Boolean {
        return cacheControl == null ||
                cacheControl.contains("no-store", true) ||
                cacheControl.contains("no-cache", true) ||
                cacheControl.contains("must-revalidate", true) ||
                cacheControl.contains("max-age=0", true)
    }
}