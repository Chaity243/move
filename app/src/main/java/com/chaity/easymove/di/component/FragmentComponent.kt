package com.chaity.easymove.di.component

import com.chaity.easymove.di.module.DataModule
import com.chaity.easymove.di.module.DomainModule
import com.chaity.easymove.di.scope.FragmentScope
import com.chaity.easymove.ui.moviedetail.DeliveryDetailFragment
import com.chaity.easymove.ui.deliverylist.DeliveryListFragment
import dagger.Subcomponent

@Subcomponent(modules = [DataModule::class, DomainModule::class])
@FragmentScope
interface FragmentComponent{
    fun inject(movieListFragment: DeliveryListFragment)
    fun inject(movieDetailFragment: DeliveryDetailFragment)
}