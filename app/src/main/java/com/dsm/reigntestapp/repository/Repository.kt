package com.dsm.reigntestapp.repository

import com.dsm.reigntestapp.network.Apis
import com.dsm.reigntestapp.network.deserializer.ArticlesResponse
import retrofit2.Response

class Repository {

    private var apis: Apis

    constructor(apis: Apis) {
        this.apis = apis
    }


    suspend fun getNews(): Response<ArticlesResponse> = apis.requestNews()


//    fun getBooks(callback: Callback<List<Book>>) = apis.requestBooks().enqueue(callback)
//
//
//    suspend fun getTicker(bookName: String): Response<Ticker> = apis.requestTicker(bookName)
//


}