package com.chaity.easymove.data.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.chaity.easymove.data.DeliveryData

import com.chaity.easymove.domain.vo.BoundaryState

// This interface is in accordance to Dependency Inversion Principle by separating the higher
// repository class from lower database class.
interface LocalData {

    fun getDeliverys() : LiveData<PagedList<DeliveryData>>
    fun getBoundaryState(): LiveData<BoundaryState<String>>
    fun insertDeliverys(movies: List<DeliveryData>)
    fun deleteDeliverys()
    fun refresh()

    fun getDelivery(id : Long) : LiveData<DeliveryData>
}