package com.chaity.easymove.data.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.chaity.easymove.data.DeliveryData

import com.chaity.easymove.domain.vo.BoundaryState
import timber.log.Timber

class DeliveryBoundaryCallback : PagedList.BoundaryCallback<DeliveryData>() {

    private val _boundaryState = MutableLiveData<BoundaryState<String>>()
    val boundaryState : LiveData<BoundaryState<String>>
        get() = _boundaryState

    companion object {
        const val DATABASE_PAGE_SIZE = 20
    }

    override fun onItemAtFrontLoaded(itemAtFront: DeliveryData) {
        Timber.d(
            "onItemAtFrontLoaded %d %s %s ", itemAtFront.id,
            itemAtFront.description, itemAtFront.hashCode()
        )
        _boundaryState.value = BoundaryState.itemLoadedAtTop(itemAtFront.description)
    }

    override fun onZeroItemsLoaded() {
        Timber.d("onZeroItemsLoaded")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(String())
    }

    override fun onItemAtEndLoaded(itemAtEnd: DeliveryData) {
        Timber.d(
            "onItemAtFrontLoaded %d %s %s ", itemAtEnd.id,
            itemAtEnd.description, itemAtEnd.hashCode()
        )
        _boundaryState.value = BoundaryState.itemLoadedAtBottom(itemAtEnd.description)
    }

    fun refresh(){
        Timber.d("refresh")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(String())
    }

}