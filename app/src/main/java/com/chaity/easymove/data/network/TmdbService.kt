package com.chaity.easymove.data.network

import com.chaity.easymove.data.DeliveryData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("deliveries")
    fun getDeliverys(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<DeliveryData>>
}
