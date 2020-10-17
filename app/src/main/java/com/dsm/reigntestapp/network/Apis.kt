package com.dsm.reigntestapp.network

import com.dsm.reigntestapp.network.deserializer.PostsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {

    @GET("search_by_date?query=android")
    fun requestNews(@Query("query") platform: String = "android"): Call<PostsResponse>


}