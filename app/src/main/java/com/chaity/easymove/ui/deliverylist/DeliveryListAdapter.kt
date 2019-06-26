package com.chaity.easymove.ui.deliverylist

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chaity.easymove.data.DeliveryData


class DeliveryListAdapter(private val listener: (Int) -> Unit) :
    PagedListAdapter<DeliveryData, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DeliveryListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieItem = getItem(position)
        if (movieItem != null) {
            (holder as DeliveryListViewHolder).bind(movieItem, listener)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<DeliveryData>() {
            override fun areItemsTheSame(oldItem: DeliveryData, newItem: DeliveryData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DeliveryData, newItem: DeliveryData): Boolean =
                oldItem == newItem
        }
    }
}