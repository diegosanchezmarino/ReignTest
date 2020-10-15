package com.dsm.reigntestapp.network.deserializer

import com.dsm.reigntestapp.model.Article

class ArticlesResponse {

    val news: ArrayList<Article>

    constructor(news: ArrayList<Article>) {
        this.news = news
    }
}