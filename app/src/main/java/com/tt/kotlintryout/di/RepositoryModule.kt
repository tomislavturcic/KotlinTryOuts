package com.tt.kotlintryout.di

import com.tt.kotlintryout.comments.CommentsRepository
import com.tt.kotlintryout.posts.PostsRepository
import org.koin.dsl.module.module

val repositoryModule = module {

    factory { PostsRepository(get()) }

    factory { CommentsRepository(get(), get()) }
}