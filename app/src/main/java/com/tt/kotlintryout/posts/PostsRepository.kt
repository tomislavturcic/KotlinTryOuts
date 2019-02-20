package com.tt.kotlintryout.posts

import com.tt.kotlintryout.data.network.PostsServiceApi
import com.tt.kotlintryout.data.network.models.PostResponse
import io.reactivex.Single

class PostsRepository(private val postsServiceApi: PostsServiceApi) {

    fun getPosts(): Single<List<PostResponse>> {
        return postsServiceApi.getPosts()
    }

}