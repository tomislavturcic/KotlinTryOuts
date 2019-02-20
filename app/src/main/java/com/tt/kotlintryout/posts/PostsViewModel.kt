package com.tt.kotlintryout.posts

import androidx.lifecycle.LiveData
import com.tt.kotlintryout.base.BaseViewModel
import com.tt.kotlintryout.base.state.StateLiveData
import com.tt.kotlintryout.base.state.ViewState
import com.tt.kotlintryout.extensions.toPostItems
import com.tt.kotlintryout.extensions.toViewState

class PostsViewModel(private val repository: PostsRepository) : BaseViewModel() {

    private val postsData = StateLiveData<List<PostItem>>()

    init {
        loadPosts()
    }

    fun loadPosts() {
        val loadPostsRequest = repository.getPosts()
        val disposable = loadPostsRequest
            .map { postsResponse -> postsResponse.toPostItems() }
            .toViewState()
            .subscribe { state -> postsData.value = state }
        addSubscription(disposable)
    }

    fun getPosts() = postsData as LiveData<ViewState<List<PostItem>>>

}