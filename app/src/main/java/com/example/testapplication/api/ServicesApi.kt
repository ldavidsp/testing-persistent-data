package com.example.testapplication.api

import com.example.testapplication.model.DataTest
import com.example.testapplication.taks.DocumentSnapshot
import retrofit2.Call
import retrofit2.http.GET

internal interface ServicesApi {
    @GET("/data")
    fun datas(): Call<MutableList<DataTest>>

    @GET("/todos")
    fun datas2(): Call<MutableList<DocumentSnapshot>>

}