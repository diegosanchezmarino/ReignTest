package com.dsm.reigntestapp.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.dsm.reigntestapp.model.Post


@Dao
interface PostDao {

    @Query("SELECT * FROM posts WHERE isDeleted LIKE :isDeleted ORDER BY createdAtI DESC")
    fun getNotDeletedPosts(isDeleted: Boolean = false): LiveData<List<Post>>

    @Insert(onConflict = IGNORE)
    fun insertNewPosts(newPosts: List<Post>): List<Long>

    @Update
    fun updatePost(newPost: Post)

//    @Query(
//        "UPDATE posts SET title = :title, storyTitle = :storyTitle,author = :author,creat WHERE id = :tid"
//    )
//    fun updatePost(
//        title: String,
//        storyTitle: String,
//        author: String,
//        creationTime: String,
//        createdAtI: String
//        storyUrl: String
//    )
//
//    @Transaction
//    fun updateOldAndInsertNew(newPosts: List<Post>) {
//        newPosts.forEach {
//            it.
//        }
//    }

}