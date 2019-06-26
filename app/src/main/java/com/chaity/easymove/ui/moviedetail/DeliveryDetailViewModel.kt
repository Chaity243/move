package com.chaity.easymove.ui.moviedetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.chaity.easymove.data.DeliveryData


import com.chaity.easymove.domain.moviedetail.DeliveryDetailUseCase
import javax.inject.Inject

class DeliveryDetailViewModel @Inject constructor(private val deliveryDetailUseCase : DeliveryDetailUseCase) : ViewModel() {
    private val _movieId = MutableLiveData<Long>()

    val delivery : LiveData<DeliveryData> = Transformations
        .switchMap(_movieId) { id ->
            deliveryDetailUseCase.getDelivery(id)
        }

    fun setDeliveryId(id : Long){
        // We need the delivery data only once. If it's already there no need to set id again as it
        // will force to get the data again fron the database. This check is helpful in case of
        // configuration changes as we call setDeliveryId from fragment.
        if(_movieId.value != id) {
            _movieId.value = id
        }
    }
}