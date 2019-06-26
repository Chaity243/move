package com.chaity.easymove.domain.movielist

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.chaity.easymove.data.DeliveryData
import com.chaity.easymove.domain.Logger

import com.chaity.easymove.domain.vo.BoundaryState
import com.chaity.easymove.domain.vo.Direction
import com.chaity.easymove.domain.vo.LoadingStatus
import javax.inject.Inject

class DeliveryListUseCase @Inject constructor(private val repository: DeliveryListRepository, private val log : Logger){

    fun getDeliverys(): LiveData<PagedList<DeliveryData>> {
        return repository.getDeliverys()
    }

    fun getBoundaryState(): LiveData<BoundaryState<String>> {
        return repository.getBoundaryState()
    }

    // Check which direction the event happened. If the user has scrolled to the top, the
    // direction will be TOP and if user has scrolled to the bottom (no more data in database)
    // then direction will be BOTTOM. If there is no data (usually first time the app start)
    // then fetch movies for current date
    fun fetchMore(itemDate: Int, direction: Direction) : LiveData<LoadingStatus> {
        val fetchDate = when (direction) {
            Direction.BOTTOM -> itemDate +20
            Direction.TOP -> if (itemDate -20>0){
                itemDate -20
            }
            else{
                itemDate -20
            }
            else -> itemDate
        }

        val dateDiff = 2

        return if (dateDiff > 0) {
            log.d("fetchMore future date %s", fetchDate)
            //if it's a future date don't fetch movies. If repository is still loading some data
            // then return loading or success to hide the progress bar.
            repository.returnLoadingOrSuccess()
        } else{
            log.d("fetchMore starting: %s", fetchDate)
            // Discard a delivery which doesn't have poster path because on our list UI we just
            // show posters
            repository.fetchMore(fetchDate){posterPath -> posterPath != null}
        }
    }

    fun refresh(){
        repository.refresh()
    }
}