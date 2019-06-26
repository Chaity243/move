package com.chaity.easymove.domain.moviedetail

import android.arch.lifecycle.LiveData
import com.chaity.easymove.data.DeliveryData
import com.chaity.easymove.domain.Logger


import javax.inject.Inject

class DeliveryDetailUseCase @Inject constructor(private val repository: DeliveryDetailRepository, private val log : Logger){
    fun getDelivery(id : Long): LiveData<DeliveryData> {
        return repository.getDelivery(id)
    }
}