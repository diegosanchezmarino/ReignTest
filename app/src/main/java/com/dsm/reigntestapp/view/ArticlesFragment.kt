package com.dsm.reigntestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.reigntestapp.ArticleViewModel
import com.dsm.reigntestapp.R
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.android.ext.android.inject

class ArticlesFragment: Fragment() {

    private val viewModel: ArticleViewModel by inject()

    var adapter = ArticlesRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.isLoadingRequest.observe(viewLifecycleOwner, Observer {isLoading ->
            swipeToRefresh.isRefreshing = isLoading
        })


        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })

        swipeToRefresh.setOnRefreshListener {
            viewModel.getNews()
        }


        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.addItemDecoration(ItemDecorator())

        viewModel.getNews()
    }




}