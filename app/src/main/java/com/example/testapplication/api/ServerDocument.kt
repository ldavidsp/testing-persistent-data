package com.example.testapplication.api

import android.app.Activity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerDocument {
    private lateinit var retrofit: Retrofit
    private const val baseurl = "https://jsonplaceholder.typicode.com" //JSON TEST

    fun health(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}