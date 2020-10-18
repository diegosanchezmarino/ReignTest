package com.dsm.reigntestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "posts")
class Post : Serializable, Cloneable {

    @ColumnInfo(name = "title")
    var title: String?

    @ColumnInfo(name = "storyTitle")
    var storyTitle: String?

    @ColumnInfo(name = "author")
    var author: String

    @ColumnInfo(name = "creationTime")
    var creationTime: String

    @ColumnInfo(name = "createdAtI")
    var createdAtI: Long

    @ColumnInfo(name = "storyUrl")
    var storyUrl: String?

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String

    @ColumnInfo(name = "isDeleted")
    var isDeleted: Boolean

    constructor(
        title: String?,
        storyTitle: String?,
        author: String,
        creationTime: String,
        id: String,
        createdAtI: Long,
        storyUrl: String?
    ) {

        this.title = title
        this.storyTitle = storyTitle
        this.id = id
        this.author = author
        this.creationTime = creationTime
        this.createdAtI = createdAtI
        this.storyUrl = storyUrl
        this.isDeleted = false
    }


    public override fun clone(): Any {
        return Post(
            title = this.title,
            author = this.author,
            creationTime = this.creationTime,
            id = this.id,
            storyTitle = storyTitle,
            createdAtI = createdAtI,
            storyUrl = storyUrl
        )
    }

}