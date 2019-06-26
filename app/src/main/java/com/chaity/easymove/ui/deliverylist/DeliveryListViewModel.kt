package com.chaity.easymove.ui.deliverylist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.chaity.easymove.domain.movielist.DeliveryListUseCase
import com.chaity.easymove.domain.vo.Direction
import com.chaity.easymove.domain.vo.LoadingStatus
import timber.log.Timber
import javax.inject.Inject

class DeliveryListViewModel @Inject constructor(private val movieListUseCase: DeliveryListUseCase) : ViewModel() {
    val movies = movieListUseCase.getDeliverys()

    //PagedList use BoundaryCallback object to send us callback about necessary events related to
    // data loading. Here we capture those events and fetch data from the network. The
    // movieListUseCase.fetchMore() function returns loading status which we observe in UI to
    // show progress bar.
    val loadingStatus : LiveData<LoadingStatus> = Transformations.switchMap(
        movieListUseCase.getBoundaryState()) {onBoundaryItemLoaded(it.itemData.toInt(), it.direction)}

    private fun onBoundaryItemLoaded(itemDate: Int, direction: Direction) : LiveData<LoadingStatus> {
        Timber.d("onBoundaryItemLoaded %s %s ", itemDate, direction)
        return movieListUseCase.fetchMore(itemDate, direction)
    }

    fun refresh() {
        Timber.d("refreshing")
        movieListUseCase.refresh()
    }
}