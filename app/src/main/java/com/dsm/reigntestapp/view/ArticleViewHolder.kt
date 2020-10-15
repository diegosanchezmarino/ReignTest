package com.dsm.reigntestapp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dsm.reigntestapp.model.Article
import kotlinx.android.synthetic.main.cell_new.view.*

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(article: Article){
        itemView.title.text = article.title ?: article.storyTitle
        itemView.author.text = article.author
        itemView.creationTime.text = article.creationTime
    }
}