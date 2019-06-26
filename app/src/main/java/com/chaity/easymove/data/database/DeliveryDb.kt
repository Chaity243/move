package com.chaity.easymove.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.chaity.easymove.data.DeliveryData

@Database(
    entities = [
        DeliveryData::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DbTypeConverters::class)
abstract class DeliveryDb : RoomDatabase() {
    abstract fun movieDao(): DeliveryDao
}