package com.tt.kotlintryout.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CommentDb::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        fun create(appContext: Context): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, "myDb.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun commentsDao(): CommentsDao

}