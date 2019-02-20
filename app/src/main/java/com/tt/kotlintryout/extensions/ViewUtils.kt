package com.tt.kotlintryout.extensions

import android.app.Activity
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tt.kotlintryout.R


fun <VH : RecyclerView.ViewHolder> RecyclerView.setup(
    hasFixedSize: Boolean = true,
    shouldUseDivider: Boolean = true,
    adapter: RecyclerView.Adapter<VH>
) {
    layoutManager = LinearLayoutManager(context)
    if (shouldUseDivider) {
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
    setHasFixedSize(hasFixedSize)
    setAdapter(adapter)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun Toolbar.showBackArrow(activity: Activity){
    setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
    setNavigationOnClickListener { activity.finish() }
}
