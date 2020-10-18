package com.dsm.reigntestapp.repository

import androidx.lifecycle.LiveData
import com.dsm.reigntestapp.model.Post
import com.dsm.reigntestapp.network.Apis
import com.dsm.reigntestapp.network.deserializer.PostsResponse
import com.dsm.reigntestapp.repository.database.PostDao
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var apis: Apis

    private var postsDao: PostDao

    constructor(apis: Apis, postDao: PostDao) {
        this.apis = apis
        this.postsDao = postDao
    }


    fun requestNewPosts(callback: Callback<PostsResponse>) {
        apis.requestNews().enqueue(callback)
    }

    fun getPostsFromDb(): LiveData<List<Post>> = postsDao.getNotDeletedPosts()

    fun insertNewPostsToDb(newPosts: List<Post>) = postsDao.insertNewPosts(newPosts)

    fun updatePost(post: Post) = postsDao.updatePost(post)

}