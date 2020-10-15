package com.dsm.reigntestapp.network

import com.dsm.reigntestapp.network.deserializer.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {
//    /search_by_date?query=android

    @GET("search_by_date?query=android")
    suspend fun requestNews(@Query("query") platform: String = "android"): Response<ArticlesResponse>


}