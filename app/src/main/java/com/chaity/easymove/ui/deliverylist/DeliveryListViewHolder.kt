package com.chaity.easymove.ui.deliverylist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaity.easymove.R
import com.chaity.easymove.data.DeliveryData

import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*


class DeliveryListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(movie: DeliveryData?, listener: (Int) -> Unit) {
        if (movie != null) {
            with(movie) {
                iv_del.contentDescription = description
                Picasso.get().load(imageUrl)
                    .placeholder(R.drawable.poster_placeholder).into(iv_del)

                tv_del.text = description
                itemView.setOnClickListener { listener.invoke(this.id) }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): DeliveryListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return DeliveryListViewHolder(view)
        }
    }
}