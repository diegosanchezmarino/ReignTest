package com.dsm.reigntestapp.view

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.dsm.reigntestapp.model.Article


class DiffCallback: DiffUtil.Callback {


    var oldList: List<Article>
    var articleList: List<Article>

    constructor(oldBookList: List<Article>, articleBookList: List<Article>) : super() {
        this.oldList = oldBookList
        this.articleList = articleBookList
    }


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == articleList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return articleList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return checkIfContentsAreTheSame(oldList[oldItemPosition], articleList[newItemPosition])
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val diff = Bundle()

        diff.putSerializable("update", articleList[newItemPosition])

        return diff
    }
    
    
    private fun checkIfContentsAreTheSame(oldItem: Article, articleItem: Article): Boolean {

        return when {
            oldItem.author != articleItem.author -> false
            oldItem.title != articleItem.title -> false
            oldItem.creationTime != articleItem.creationTime -> false
            oldItem.storyTitle != articleItem.storyTitle -> false
            else -> true
        }
    }

}