package com.dsm.reigntestapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.reigntestapp.model.Article
import com.dsm.reigntestapp.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArticleViewModel: ViewModel {

    private val repository: Repository


    var isLoadingRequest: MutableLiveData<Boolean> = MutableLiveData()


    var articleList: MutableLiveData<ArrayList<Article>> = MutableLiveData()

    constructor(repository: Repository) : super() {
        this.repository = repository
    }


    fun getNews(){
        viewModelScope.launch {

            isLoadingRequest.postValue(true)

            var response = async { repository.getNews() }.await()

            if(response.isSuccessful)
                articleList.postValue(response.body()!!.news)

            isLoadingRequest.postValue(false)

        }
    }


}