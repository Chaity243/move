package com.chaity.easymove.di.module

import com.chaity.easymove.data.database.LocalDataImpl
import com.chaity.easymove.data.network.RemoteDataImpl
import com.chaity.easymove.data.repository.LocalData
import com.chaity.easymove.data.repository.RemoteData
import com.chaity.easymove.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface DataModule{

    @Binds
    @FragmentScope
    fun bindLocalData (localData : LocalDataImpl) : LocalData

    @Binds
    @FragmentScope
    fun bindRemoteData (remoteData : RemoteDataImpl) : RemoteData

}