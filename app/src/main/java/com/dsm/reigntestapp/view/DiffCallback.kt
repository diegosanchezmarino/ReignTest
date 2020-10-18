package com.dsm.reigntestapp.view

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil.Callback
import com.dsm.reigntestapp.model.Post
import timber.log.Timber


class DiffCallback: Callback {


    private var oldList: List<Post>
    private var newList: List<Post>

    constructor(oldBookList: List<Post>, postBookList: List<Post>) : super() {
        this.oldList = oldBookList
        this.newList = postBookList
    }


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val result = oldList[oldItemPosition].id == newList[newItemPosition].id
        Timber.v("Are items the same: %s", result)

        return result

    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val result = checkIfContentsAreTheSame(oldList[oldItemPosition], newList[newItemPosition])

        Timber.v("Are contents the same: %s", result)
        return result
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val diff = Bundle()

        diff.putSerializable("update", newList[newItemPosition])

        return diff
    }
    
    
    private fun checkIfContentsAreTheSame(oldItem: Post, postItem: Post): Boolean {

        return when {
            oldItem.author != postItem.author -> false
            oldItem.title != postItem.title -> false
            oldItem.creationTime != postItem.creationTime -> false
            oldItem.storyTitle != postItem.storyTitle -> false
            else -> true
        }
    }




}