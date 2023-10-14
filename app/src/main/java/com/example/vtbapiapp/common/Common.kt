package com.example.vtbapiapp.common

import com.google.gson.GsonBuilder
import com.example.vtbapiapp.`interface`.RetrofitServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Common {
    private const val BASE_URL = "http://192.168.253.192:8081/"
    private val gson = GsonBuilder()
        .setLenient() // Разрешить неправильно отформатированный JSON
        .create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val retrofitService: RetrofitServices
        get() = retrofit.create(RetrofitServices::class.java)


}