package com.tt.kotlintryout.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class CommentsDao {

    @Query("SELECT * from comments WHERE postId=:postId ORDER BY timestamp DESC")
    abstract fun getUsersCommentsForPost(postId: Int): Single<List<CommentDb>>

    @Insert
    abstract fun addUserComment(commentDb: CommentDb): Completable
}