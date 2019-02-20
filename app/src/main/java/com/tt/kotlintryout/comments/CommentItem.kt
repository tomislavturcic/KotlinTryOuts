package com.tt.kotlintryout.comments

data class CommentItem(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)