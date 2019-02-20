package com.tt.kotlintryout.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ItemAdapter<T: Any>(private val itemClickListener : ItemClickListener<T>?) : RecyclerView.Adapter<ItemViewHolder<T>>() {

    private val items : ArrayList<T> = ArrayList()

    fun updateItems(newItems : List<T>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<T> {
        return createViewHolder(LayoutInflater.from(parent.context), parent, itemClickListener)
    }

    abstract fun createViewHolder(inflater : LayoutInflater, parent: ViewGroup, itemClickListener : ItemClickListener<T>?) : ItemViewHolder<T>


}