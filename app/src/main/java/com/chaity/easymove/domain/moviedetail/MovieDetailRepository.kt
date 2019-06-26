package com.chaity.easymove.domain.moviedetail

import android.arch.lifecycle.LiveData
import com.chaity.easymove.data.DeliveryData



// As we need to interact with repostiory which is in data layer. Implementing Dependency Inversion
// Principle we create an interface which concrete respository class in data layer implements.
// This way we remove any dependency on data layer from domain layer
interface DeliveryDetailRepository {
    fun getDelivery(id : Long): LiveData<DeliveryData>
}