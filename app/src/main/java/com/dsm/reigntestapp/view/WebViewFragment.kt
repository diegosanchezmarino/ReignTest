package com.dsm.reigntestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.dsm.reigntestapp.PostsViewModel
import com.dsm.reigntestapp.R
import kotlinx.android.synthetic.main.fragment_web_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class WebViewFragment : Fragment() {

    private val viewModel: PostsViewModel by sharedViewModel()

    var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        this.progressBar = progressbar
        webView.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressBar?.progress = newProgress
                if(newProgress == 100)
                    progressBar?.visibility = GONE
            }
        }


        viewModel.selectedPost.value?.storyUrl?.let {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(it)
        }

    }



}