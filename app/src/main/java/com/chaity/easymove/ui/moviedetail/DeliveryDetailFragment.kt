package com.chaity.easymove.ui.moviedetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaity.easymove.DeliverySampleApp
import com.chaity.easymove.R
import com.chaity.easymove.data.DeliveryData

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_delivery_detail.*
import javax.inject.Inject


class DeliveryDetailFragment : Fragment() {
    @Inject
    lateinit var deliveryDetailViewModelFactory: DeliveryDetailViewModelFactory

    override fun onAttach(context: Context?) {
        DeliverySampleApp.instance.getApplicationComponent().plusFragmentComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_delivery_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)
        val movieDetailViewModel = ViewModelProviders.of(
                this,deliveryDetailViewModelFactory)
                .get(DeliveryDetailViewModel::class.java)

        movieDetailViewModel.delivery.observe(viewLifecycleOwner, Observer { it ->
            it?.let { setDeliveryData(it) }
        })

        val params = DeliveryDetailFragmentArgs.fromBundle(arguments)
        movieDetailViewModel.setDeliveryId(params.movieId.toLong())

    }

    private fun setDeliveryData(delivery: DeliveryData) {
        title.text = delivery.description
        overview.text = delivery.description

        delivery.imageUrl?.let {
            backdrop.visibility = View.VISIBLE
            Picasso.get().load(  delivery.imageUrl)
                    .placeholder(R.drawable.poster_placeholder).into(backdrop)
        }


        Picasso.get().load(  delivery.imageUrl)
                .placeholder(R.drawable.poster_placeholder).into(poster)

    }
}
