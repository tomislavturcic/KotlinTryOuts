package com.tt.kotlintryout.di

import com.tt.kotlintryout.comments.CommentsViewModel
import com.tt.kotlintryout.posts.PostsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelsModule : Module = module {
    viewModel { PostsViewModel(get()) }
    viewModel { (postId : Int) -> CommentsViewModel(postId, get()) }
}