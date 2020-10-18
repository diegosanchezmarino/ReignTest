package com.dsm.reigntestapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsm.reigntestapp.PostsViewModel
import com.dsm.reigntestapp.R
import com.dsm.reigntestapp.model.Post
import com.dsm.reigntestapp.network.NetworkStatus
import com.dsm.reigntestapp.network.NetworkStatus.*
import kotlinx.android.synthetic.main.fragment_posts_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class PostsFragment: Fragment(), PostListInteraction {

    private val viewModel: PostsViewModel by sharedViewModel()

    private var adapter = PostsAdapter(this)

    private var toast: Toast? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        toast = makeText(requireContext(), R.string.error_post_not_found, Toast.LENGTH_SHORT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.requestStatus.observe(viewLifecycleOwner, Observer { status ->

            when (status){
                Loading -> {
                    swipeToRefresh.isRefreshing = true
                }
                Error -> {
                    toast?.setText(R.string.error_fetching_data)
                    toast?.show()
                    swipeToRefresh.isRefreshing = false
                }
                Success -> {
                    swipeToRefresh.isRefreshing = false
                }
            }
        })


        viewModel.postList.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })

        swipeToRefresh.setOnRefreshListener {
            viewModel.pullNewPosts()
        }


        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.addItemDecoration(ItemDecorator())
        ItemTouchHelper(SwipeToDeleteCallback(adapter, requireContext())).attachToRecyclerView(recyclerview)

        viewModel.pullNewPosts()


    }

    override fun pressed(post: Post) {

        post.storyUrl?.let {
            viewModel.selectPost(post)

            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.enter_from_right)
                .add(R.id.container, WebViewFragment::class.java, null)
                .addToBackStack(this.javaClass.name)
                .commit()
        } ?: run {
            toast?.setText(R.string.error_post_not_found)
            toast?.show()
        }


    }

    override fun swiped(post: Post) {
        viewModel.setPostAsDeleted(post)
    }
}