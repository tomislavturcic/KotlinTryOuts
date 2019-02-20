package com.tt.kotlintryout.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tt.kotlintryout.R
import com.tt.kotlintryout.base.adapter.ItemClickListener
import com.tt.kotlintryout.base.adapter.ItemViewHolder
import com.tt.kotlintryout.base.adapter.ViewStateAdapter
import com.tt.kotlintryout.common.SimpleErrorViewHolder
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(
    private val itemClickListener: ItemClickListener<PostItem>?,
    private val errorClickListener: View.OnClickListener
) : ViewStateAdapter<PostItem>() {

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): ItemViewHolder<PostItem> {
        return ViewHolder(inflater.inflate(R.layout.item_post, parent, false), itemClickListener)
    }

    override fun createErrorViewHolder(itemView: View): RecyclerView.ViewHolder {
        return SimpleErrorViewHolder(itemView, errorClickListener)
    }

    inner class ViewHolder(view: View, itemClickListener: ItemClickListener<PostItem>?) :
        ItemViewHolder<PostItem>(view, itemClickListener) {

        private val txtPostTitle = itemView.txtPostTitle
        private val txtPostBody = itemView.txtPostBody

        override fun bind(item: PostItem) {
            super.bind(item)
            txtPostTitle.text = item.title
            txtPostBody.text = item.body
        }

    }
}