package com.tt.kotlintryout.main

import android.content.Context
import android.content.Intent
import com.tt.kotlintryout.R
import com.tt.kotlintryout.posts.PostsActivity

enum class MainListItem(val titleTextId : Int) {

    POSTS(R.string.posts_title){
        override fun createIntent(context: Context) : Intent {
            return Intent(context, PostsActivity::class.java)
        }
    },

    ALBUMS(R.string.albums_title) {
        override fun createIntent(context: Context): Intent? {
            return null
        }
    },

    NOTES(R.string.notes_title){
        override fun createIntent(context: Context): Intent? {
            return null
        }
    };

    abstract fun createIntent(context: Context) : Intent?

}