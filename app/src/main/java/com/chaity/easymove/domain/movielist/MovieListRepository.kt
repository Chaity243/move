package com.chaity.easymove.domain.movielist

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.chaity.easymove.data.DeliveryData

import com.chaity.easymove.domain.vo.BoundaryState
import com.chaity.easymove.domain.vo.LoadingStatus

// As we need to interact with repostiory which is in data layer. Implementing Dependency Inversion
// Principle we create an interface which concrete respository class in data layer implements.
// This way we remove any dependency on data layer from domain layer
interface DeliveryListRepository {
    fun getDeliverys(): LiveData<PagedList<DeliveryData>>
    fun getBoundaryState(): LiveData<BoundaryState<String>>
    fun fetchMore(fetchDate: Int, predicate: (String?) -> (Boolean)) : LiveData<LoadingStatus>
    fun returnLoadingOrSuccess() : LiveData<LoadingStatus>
    fun refresh()
}