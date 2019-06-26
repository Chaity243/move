package com.chaity.easymove.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chaity.easymove.data.DeliveryData


@Dao
interface DeliveryDao {

    @Query("SELECT * FROM delivery")
    fun getDataSourcefactory(): DataSource.Factory<Int, DeliveryData>

    @Query("SELECT * FROM delivery WHERE id= :id")
    fun getDelivery(id: Int) : LiveData<DeliveryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeliveries(movies: List<DeliveryData>)

    @Query("DELETE FROM delivery")
    fun deleteDeliveries()
}