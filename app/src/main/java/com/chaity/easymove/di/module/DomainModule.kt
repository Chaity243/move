package com.chaity.easymove.di.module

import com.chaity.easymove.data.repository.DeliveryDetailRepositoryImpl
import com.chaity.easymove.data.repository.DeliveryListRepositoryImpl
import com.chaity.easymove.di.scope.FragmentScope
import com.chaity.easymove.domain.moviedetail.DeliveryDetailRepository
import com.chaity.easymove.domain.movielist.DeliveryListRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule{

    @Binds
    @FragmentScope
    fun bindDeliveryListRepository(deliveryListRepository : DeliveryListRepositoryImpl) : DeliveryListRepository

    @Binds
    @FragmentScope
    fun bindDeliveryDetailRepository(deliveryDetailRepository : DeliveryDetailRepositoryImpl) : DeliveryDetailRepository
}