package com.tt.kotlintryout.extensions

import com.tt.kotlintryout.comments.CommentItem
import com.tt.kotlintryout.data.network.models.CommentResponse
import com.tt.kotlintryout.data.network.models.PostResponse
import com.tt.kotlintryout.posts.PostItem

fun List<PostResponse>.toPostItems(): List<PostItem> {
    return this.map { postResponse -> postResponse.toPostItem() }
}

fun PostResponse.toPostItem(): PostItem {
    return PostItem(this.userId, this.id, this.title, this.body)
}

fun List<CommentResponse>.toCommentItems(): List<CommentItem> {
    return this.map { commentResponse -> commentResponse.toCommentItem() }
}

fun CommentResponse.toCommentItem(): CommentItem {
    return CommentItem(this.id, this.postId, this.name, this.email, this.body)
}
