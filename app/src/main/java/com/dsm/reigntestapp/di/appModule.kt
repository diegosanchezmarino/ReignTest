package com.dsm.reigntestapp.di

import com.dsm.reigntestapp.ArticleViewModel
import com.dsm.reigntestapp.repository.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Repository(get()) }
    viewModel { ArticleViewModel(get()) }


}