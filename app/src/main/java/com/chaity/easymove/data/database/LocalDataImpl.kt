package com.chaity.easymove.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.chaity.easymove.AppExecutors
import com.chaity.easymove.data.DeliveryData
import com.chaity.easymove.data.repository.LocalData


import com.chaity.easymove.domain.vo.BoundaryState
import javax.inject.Inject

class LocalDataImpl @Inject constructor(private val appExecutors: AppExecutors,
                                        private val movieDao: DeliveryDao) :
    LocalData {
    override fun getDelivery(id: Long): LiveData<DeliveryData> {
        return movieDao.getDelivery(id.toInt())
    }

    private val boundaryCallback =
        DeliveryBoundaryCallback()

    override fun getDeliverys(): LiveData<PagedList<DeliveryData>> {
        val dataSourceFactory = movieDao.getDataSourcefactory()
        return LivePagedListBuilder(dataSourceFactory, DeliveryBoundaryCallback.DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

  /*  override fun getDelivery(id : Int): LiveData<DeliveryData> {
        return movieDao.getDelivery(id)
    }*/

    override fun getBoundaryState(): LiveData<BoundaryState<String>> {
        return boundaryCallback.boundaryState
    }

    override fun insertDeliverys(movies: List<DeliveryData>) {
        appExecutors.diskIO().execute {
            movieDao.insertDeliveries(movies)
        }
    }

    override fun deleteDeliverys() {
        movieDao.deleteDeliveries()
    }

    override fun refresh() {
        boundaryCallback.refresh()
    }
}