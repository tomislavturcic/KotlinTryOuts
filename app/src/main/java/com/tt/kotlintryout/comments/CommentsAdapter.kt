package com.tt.kotlintryout.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tt.kotlintryout.R
import com.tt.kotlintryout.base.adapter.ItemClickListener
import com.tt.kotlintryout.base.adapter.ItemViewHolder
import com.tt.kotlintryout.base.adapter.ViewStateAdapter
import com.tt.kotlintryout.common.SimpleErrorViewHolder
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsAdapter(
    private val itemClickListener: ItemClickListener<CommentItem>,
    private val errorClickListener: View.OnClickListener
) : ViewStateAdapter<CommentItem>() {

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): ItemViewHolder<CommentItem> {
        return CommentViewHolder(inflater.inflate(R.layout.item_comment, parent, false), itemClickListener)
    }

    override fun createErrorViewHolder(itemView: View): RecyclerView.ViewHolder {
        return SimpleErrorViewHolder(itemView, errorClickListener)
    }

    inner class CommentViewHolder(itemView: View, itemClickListener: ItemClickListener<CommentItem>) :
        ItemViewHolder<CommentItem>(itemView, itemClickListener) {

        private val txtName = itemView.txtCommentName
        private val txtEmail = itemView.txtCommentEmail
        private val txtBody = itemView.txtCommentBody

        override fun bind(item: CommentItem) {
            super.bind(item)
            txtName.text = item.name
            txtEmail.text = item.email
            txtBody.text = item.body
        }
    }
}