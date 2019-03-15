package com.example.testapplication.api

import okhttp3.CacheControl
import okhttp3.Interceptor

object CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
       /* if (!ApiManager.isNetworkAvailable()) {
            request = request.newBuilder()
                .removeHeader("Pragma")
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }*/

        val originalResponse = chain.proceed(request)
        val cacheControl = originalResponse.header("Cache-Control")

        return if (isRemoteNoCache(cacheControl)) {
            originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=" + 5000)
                .build()
        } else
            originalResponse
    }

    private fun isRemoteNoCache(cacheControl: String?): Boolean =
        cacheControl == null ||
                cacheControl.contains("no-store", true) ||
                cacheControl.contains("no-cache", true) ||
                cacheControl.contains("must-revalidate", true) ||
                cacheControl.contains("max-age=0", true)

}