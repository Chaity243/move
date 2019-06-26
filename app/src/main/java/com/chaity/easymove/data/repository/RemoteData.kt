package com.chaity.easymove.data.repository

import com.chaity.easymove.data.DeliveryData
import com.chaity.easymove.domain.vo.LoadingStatus

// This interface is in accordance to Dependency Inversion Principle by separating the higher
// repository class from lower network class.
interface RemoteData {
    fun fetchItems(fetchDate: Int,
                   onSuccess: (movies : List<DeliveryData>) -> Unit,
                   onStatus: (status : LoadingStatus) -> Unit)
}