package com.example.recyclerview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAlbumAPI {
    @GET("albums")
    fun getAlbumData() : Call<List<Album>>
}