package com.chaity.easymove.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.chaity.easymove.data.database.DeliveryDb
import com.chaity.easymove.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DbModule(){

    @Provides
    @ApplicationScope
    fun provideDeliveryDb(application: Application) =
        Room.databaseBuilder(
            application,
            DeliveryDb::class.java, "Delivery.db"
        ).build()

    @Provides
    @ApplicationScope
    fun provideDeliveryDao(movieDb: DeliveryDb) = movieDb.movieDao()
}