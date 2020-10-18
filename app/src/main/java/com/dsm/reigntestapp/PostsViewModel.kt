package com.dsm.reigntestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.reigntestapp.model.Post
import com.dsm.reigntestapp.network.NetworkStatus
import com.dsm.reigntestapp.network.NetworkStatus.*
import com.dsm.reigntestapp.network.deserializer.PostsResponse
import com.dsm.reigntestapp.repository.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel : ViewModel {

    private val repository: Repository

    var selectedPost: MutableLiveData<Post> = MutableLiveData()

    var requestStatus: MutableLiveData<NetworkStatus> = MutableLiveData()

    var postList: LiveData<List<Post>>

    constructor(repository: Repository) : super() {
        this.repository = repository
        postList = repository.getPostsFromDb()
    }


    fun pullNewPosts() {


        requestStatus.postValue(Loading)

        repository.requestNewPosts(object : Callback<PostsResponse> {
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                requestStatus.postValue(Error)
            }

            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if (response.isSuccessful) {
                    requestStatus.postValue(Success)

                    viewModelScope.launch(IO){
                        async { repository.insertNewPostsToDb(response.body()!!.news) }
                    }
                }
                else{
                    requestStatus.postValue(Error)
                }
            }

        })

    }

    fun setPostAsDeleted(post: Post) {
        viewModelScope.launch(IO) {
            post.isDeleted = true
            async { repository.updatePost(post) }
        }
    }


    fun selectPost(post: Post) {
        selectedPost.value = post
    }


}