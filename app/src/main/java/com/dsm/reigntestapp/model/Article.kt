package com.dsm.reigntestapp.model

import java.io.Serializable

class Article : Serializable, Cloneable {

    var title: String?
    var storyTitle: String
    var author: String
    var creationTime: String
    var createdAtI: Long
    var id: String

    constructor(
        title: String?,
        storyTitle: String,
        author: String,
        creationTime: String,
        id: String,
        createdAtI: Long
    ) {

        this.title = title
        this.storyTitle = storyTitle
        this.id = id
        this.author = author
        this.creationTime = creationTime
        this.createdAtI = createdAtI
    }


    public override fun clone(): Any {
        return Article(
            title = this.title,
            author = this.author,
            creationTime = this.creationTime,
            id = this.id,
            storyTitle = storyTitle,
            createdAtI = createdAtI
        )
    }
}