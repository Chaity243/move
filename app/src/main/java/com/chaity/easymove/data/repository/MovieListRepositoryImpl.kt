package com.chaity.easymove.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.chaity.easymove.AppExecutors
import com.chaity.easymove.data.DeliveryData

import com.chaity.easymove.domain.movielist.DeliveryListRepository
import com.chaity.easymove.domain.vo.BoundaryState
import com.chaity.easymove.domain.vo.LoadingStatus
import com.chaity.easymove.domain.vo.Status
import timber.log.Timber
import javax.inject.Inject


class DeliveryListRepositoryImpl @Inject constructor (
    private val appExecutors: AppExecutors,
    private val localData: LocalData,
    private val remoteData: RemoteData
) : DeliveryListRepository {
    private val loadingStatus = MutableLiveData<LoadingStatus>()

    override
    fun getDeliverys(): LiveData<PagedList<DeliveryData>> {
        return localData.getDeliverys()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<String>> {
        return localData.getBoundaryState()
    }

    override fun refresh() {
        localData.refresh()
    }

    override
    fun fetchMore(fetchDate: Int, predicate: (String?) -> (Boolean)) : LiveData<LoadingStatus> {
        if (loadingStatus.value == null || loadingStatus.value?.status != Status.LOADING) {
            loadingStatus.value = LoadingStatus.loading()
            Timber.d("fetchMore starting: %s", fetchDate)
            remoteData.fetchItems(fetchDate, { movies ->
                appExecutors.diskIO().execute {
                   localData.insertDeliverys(movies.filter { predicate(it.imageUrl)})
                    Timber.d("fetchMore saved: %s", fetchDate)
                }
            }, {status ->
                loadingStatus.value = status
            })
        } else{
            Timber.d("fetchMore already loading %s", fetchDate)
        }
        return loadingStatus
    }

    override
    fun returnLoadingOrSuccess() : LiveData<LoadingStatus>{
        if (loadingStatus.value == null || loadingStatus.value?.status != Status.LOADING) {
            loadingStatus.value = LoadingStatus.success()
        }
        return loadingStatus
    }
}
