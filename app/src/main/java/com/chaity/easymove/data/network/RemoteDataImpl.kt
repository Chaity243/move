package com.chaity.easymove.data.network

import com.chaity.easymove.data.DeliveryData
import com.chaity.easymove.data.repository.RemoteData
import com.chaity.easymove.domain.vo.ErrorCode
import com.chaity.easymove.domain.vo.LoadingStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RemoteDataImpl @Inject constructor(private val tmdbService: TmdbService) :
    RemoteData {
    override fun fetchItems(
            fetchDate: Int,
            onSuccess: (movies: List<DeliveryData>) -> Unit,
            onStatus: (status: LoadingStatus) -> Unit
    ) {
        val call = tmdbService.getDeliverys(0,20)
        call.enqueue(object : Callback<List<DeliveryData>?> {
            override fun onResponse(call: Call<List<DeliveryData>?>?, response: Response<List<DeliveryData>?>?) {
                if (response != null) {
                    if (response.body()?.size == 0) {
                        onStatus(
                            LoadingStatus.error(
                            ErrorCode.NO_DATA))
                    } else {
                        response.body()?.let {
                            onSuccess(it)
                            Timber.d("fetchMore saved: %s", fetchDate)
                        }
                        onStatus(LoadingStatus.success())
                    }
                }
            }

            override fun onFailure(call: Call<List<DeliveryData>?>?, t: Throwable?) {
                if (t is IOException) {
                    onStatus(
                        LoadingStatus.error(
                        ErrorCode.NETWORK_ERROR, t.message))
                } else {
                    onStatus(
                        LoadingStatus.error(
                        ErrorCode.UNKNOWN_ERROR, null))
                }
            }
        })
    }
}