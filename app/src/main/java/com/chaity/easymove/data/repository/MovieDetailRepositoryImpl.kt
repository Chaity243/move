package com.chaity.easymove.data.repository

import android.arch.lifecycle.LiveData
import com.chaity.easymove.data.DeliveryData


import com.chaity.easymove.domain.moviedetail.DeliveryDetailRepository
import javax.inject.Inject


class DeliveryDetailRepositoryImpl @Inject constructor(
    private val localData: LocalData
) : DeliveryDetailRepository {
    override
    fun getDelivery(id : Long): LiveData<DeliveryData> {
        return localData.getDelivery(id)
    }
}
