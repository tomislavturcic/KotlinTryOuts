package com.tt.kotlintryout.comments

import com.tt.kotlintryout.data.local.AppDatabase
import com.tt.kotlintryout.data.local.CommentDb
import com.tt.kotlintryout.data.network.PostsServiceApi
import com.tt.kotlintryout.data.network.models.CommentResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class CommentsRepository(
    private val postsServiceApi: PostsServiceApi,
    database: AppDatabase
) {

    private val commentsDao = database.commentsDao()

    fun getCommentsForPost(postId: Int): Single<List<CommentResponse>> {
        return postsServiceApi.getComments()
            .map { comments -> comments.filter { comment -> comment.postId == postId } }
            .zipWith(
                getUserCommentsFromDb(postId),
                BiFunction { commentsFromApi, userCommentsFromDb ->
                    listOf(
                        userCommentsFromDb,
                        commentsFromApi
                    ).flatten()
                }
            )
    }


    private fun getUserCommentsFromDb(postId: Int): Single<List<CommentResponse>> {
        return commentsDao.getUsersCommentsForPost(postId)
            .map { commentsDb ->
                // Just some mock data
                val userName = "Tomislav Turcic"
                val userEmail = "tomislav.turcic.business@gmail.com"
                commentsDb.map { commentDb ->
                    CommentResponse(postId, commentDb.id!! + 1000, userName, userEmail, commentDb.body)
                }
            }
    }

    fun addUserCommentForPost(postId: Int, comment: String): Completable {
        return if (comment.isBlank()) {
            Completable.error(Throwable("Comment can't be empty."))
        } else {
            return commentsDao.addUserComment(CommentDb(postId = postId, body = comment))
        }
    }

}

