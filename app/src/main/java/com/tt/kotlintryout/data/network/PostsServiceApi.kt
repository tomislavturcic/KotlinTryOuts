package com.tt.kotlintryout.data.network

import com.tt.kotlintryout.data.network.models.CommentResponse
import com.tt.kotlintryout.data.network.models.PostResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PostsServiceApi {

    @GET("posts")
    fun getPosts() : Single<List<PostResponse>>

    @GET("comments")
    fun getComments() : Single<List<CommentResponse>>

}