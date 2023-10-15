package com.example.vtbapiapp.common

import com.google.gson.GsonBuilder
import com.example.vtbapiapp.`interface`.RetrofitServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Common {
    private const val BASE_URL = "http://192.168.253.192:8081/"
    val client = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS) // Установка таймаута чтения в 60 секунд
        .build()
    private val gson = GsonBuilder()
        .setLenient() // Разрешить неправильно отформатированный JSON
        .create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val retrofitService: RetrofitServices
        get() = retrofit.create(RetrofitServices::class.java)


}