package com.tt.kotlintryout.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tt.kotlintryout.R
import com.tt.kotlintryout.base.adapter.ItemAdapter
import com.tt.kotlintryout.base.adapter.ItemClickListener
import com.tt.kotlintryout.base.adapter.ItemViewHolder

class MainAdapter(itemClickListener: ItemClickListener<MainListItem>) : ItemAdapter<com.tt.kotlintryout.main.MainListItem>(itemClickListener) {

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        itemClickListener: ItemClickListener<MainListItem>?
    ): ItemViewHolder<MainListItem> {
        return ViewHolder(inflater.inflate(R.layout.item_main, parent, false), itemClickListener)
    }

    inner class ViewHolder(view: View, itemClickListener: ItemClickListener<MainListItem>?) : ItemViewHolder<MainListItem>(view, itemClickListener) {

        private val textView = itemView as TextView

        override fun bind(item: MainListItem) {
            super.bind(item)
            textView.setText(item.titleTextId)
        }

    }
}