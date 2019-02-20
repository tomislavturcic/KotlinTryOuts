package com.tt.kotlintryout.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder<T: Any>(view: View, itemClickListener: ItemClickListener<T>?) : RecyclerView.ViewHolder(view) {

    private lateinit var item: T

    init {
        itemView.setOnClickListener {
            itemClickListener?.onItemClicked(item)
        }
    }

    open fun bind(item: T) {
        this.item = item
    }
}