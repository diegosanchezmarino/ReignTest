package com.dsm.reigntestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dsm.reigntestapp.R
import com.dsm.reigntestapp.model.Post

class PostsAdapter : Adapter<PostsViewHolder> {


    private val news = ArrayList<Post>()

    private var postListInteraction: PostListInteraction

    constructor(postListInteraction: PostListInteraction) {
        this.postListInteraction = postListInteraction
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.cell_post
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return PostsViewHolder(view, postListInteraction)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bindView(news[position])
    }

    override fun onBindViewHolder(
        holder: PostsViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {


        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        } else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                if (key == "update") {
                    var updatedNew = o.getSerializable(key) as Post
                    holder.bindView(post = updatedNew)
                }
            }
        }


    }


    fun updateData(postList: List<Post>) {
        DiffUtil.calculateDiff(DiffCallback(news, postList)).dispatchUpdatesTo(this)
        news.clear()
        postList.forEach { news.add(it.clone() as Post) }
    }


}