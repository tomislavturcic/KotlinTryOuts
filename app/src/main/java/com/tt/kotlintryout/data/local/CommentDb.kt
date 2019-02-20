package com.tt.kotlintryout.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentDb(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    val postId : Int,
    val body : String,
    val timestamp : Long = System.currentTimeMillis()
)