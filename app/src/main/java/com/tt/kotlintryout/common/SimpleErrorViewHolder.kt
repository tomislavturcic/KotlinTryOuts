package com.tt.kotlintryout.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_error_view.view.*

class SimpleErrorViewHolder(
    itemView: View,
    errorClickListener: View.OnClickListener
) :
    RecyclerView.ViewHolder(itemView) {

    private val btnRetry = itemView.btnRetry

    init {
        btnRetry.setOnClickListener(errorClickListener)
    }
}