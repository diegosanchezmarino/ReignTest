package com.dsm.reigntestapp.view

import com.dsm.reigntestapp.model.Post

interface PostListInteraction {

    fun pressed(post: Post)
    fun swiped(post: Post)
}