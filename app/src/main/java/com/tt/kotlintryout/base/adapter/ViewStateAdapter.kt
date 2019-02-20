package com.tt.kotlintryout.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tt.kotlintryout.R
import com.tt.kotlintryout.base.state.*

abstract class ViewStateAdapter<T : Any>(
    private val loadingLayoutId: Int = R.layout.item_progress_bar,
    private val errorLayoutId: Int = R.layout.item_error_view,
    private val emptyLayoutId: Int = R.layout.item_empty_view
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        private const val TYPE_LOADING = 0
        private const val TYPE_ERROR = 1
        private const val TYPE_EMPTY = 2
        private const val TYPE_CONTENT = 3
    }

    private var viewState: ViewState<List<T>>? = null

    fun updateState(newViewState: ViewState<List<T>>) {
        this.viewState = newViewState
        notifyDataSetChanged()  // TODO - provide proper data set change implementation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_LOADING -> SimpleViewHolder(inflater.inflate(loadingLayoutId, parent, false))
            TYPE_ERROR -> createErrorViewHolder(inflater.inflate(errorLayoutId, parent, false))
            TYPE_EMPTY -> SimpleViewHolder(inflater.inflate(emptyLayoutId, parent, false))
            else -> createViewHolder(inflater, parent)
        }
    }

    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): ItemViewHolder<T>

    protected open fun createErrorViewHolder(itemView: View) : RecyclerView.ViewHolder {
        return SimpleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val state = viewState
        return when {
            state == null -> 0
            state.isEmptyErrorOrLoading() -> 1
            else -> state.data!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        val state = viewState
        if (position == 0 && state != null) {
            when {
                state.isLoading -> return TYPE_LOADING
                state.isError() -> return TYPE_ERROR
                state.isEmpty() -> return TYPE_EMPTY
            }
        }
        return TYPE_CONTENT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_CONTENT) {
            val state = viewState
            state?.data?.get(position)?.let { (holder as ItemViewHolder<T>).bind(it) }
        }
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}