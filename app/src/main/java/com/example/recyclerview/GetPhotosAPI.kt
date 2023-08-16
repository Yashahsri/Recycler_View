package com.example.recyclerview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetPhotosAPI {
    @GET("photos")
    fun getPhotosData(@Query("albumId") albumId: String) : Call<List<Photos>>




}