package com.tt.kotlintryout.posts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostItem(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String

) : Parcelable