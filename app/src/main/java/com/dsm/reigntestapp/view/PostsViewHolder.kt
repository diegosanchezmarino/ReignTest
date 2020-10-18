package com.dsm.reigntestapp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dsm.reigntestapp.DateFormatter
import com.dsm.reigntestapp.model.Post
import kotlinx.android.synthetic.main.cell_post.view.*

class PostsViewHolder : RecyclerView.ViewHolder {

    var post: Post? = null
    var postListInteraction: PostListInteraction

    private val dateFormatter: DateFormatter = DateFormatter()

    constructor(itemView: View, postListInteraction: PostListInteraction) : super(itemView) {

        this.postListInteraction = postListInteraction

        itemView.setOnClickListener{
            post?.let { this.postListInteraction.pressed(it) }
        }
    }




    fun bindView(post: Post){
        this.post = post
        itemView.title.text = post.title ?: post.storyTitle ?: "Title not found"
        itemView.author.text = post.author
        itemView.creationTime.text = dateFormatter.formatDate(post.creationTime)
    }
}