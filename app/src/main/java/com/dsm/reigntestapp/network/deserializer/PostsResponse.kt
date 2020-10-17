package com.dsm.reigntestapp.network.deserializer

import com.dsm.reigntestapp.model.Post

class PostsResponse {

    val news: ArrayList<Post>

    constructor(news: ArrayList<Post>) {
        this.news = news
    }
}