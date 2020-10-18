package com.dsm.reigntestapp.di

import com.dsm.reigntestapp.PostsViewModel
import com.dsm.reigntestapp.repository.Repository
import com.dsm.reigntestapp.view.PostsFragment
import com.dsm.reigntestapp.view.WebViewFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Repository(get(),get()) }

    factory { PostsFragment() }
    factory { WebViewFragment() }

    viewModel { PostsViewModel(get()) }

}