package com.example.recyclerview

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    val baseURL = "https://jsonplaceholder.typicode.com"
    fun  getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseURL).
        addConverterFactory(GsonConverterFactory.create()).build()
    }

}