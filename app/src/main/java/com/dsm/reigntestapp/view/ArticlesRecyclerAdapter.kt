package com.dsm.reigntestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsm.reigntestapp.R
import com.dsm.reigntestapp.model.Article

class ArticlesRecyclerAdapter : RecyclerView.Adapter<ArticleViewHolder>() {


    private val news = ArrayList<Article>()



    override fun getItemViewType(position: Int): Int {
        return R.layout.cell_new
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindView(news[position])
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int, payloads: MutableList<Any>) {


        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                if (key == "update") {
                    var updatedNew = o.getSerializable(key) as Article
                    holder.bindView(article = updatedNew)
                }
            }
        }


    }

    fun updateData(articleList: List<Article>) {

        val diffResult = DiffUtil.calculateDiff(DiffCallback(news, articleList))
        diffResult.dispatchUpdatesTo(this)
        news.clear()
        articleList.forEach { news.add(it.clone() as Article) }
    }


}