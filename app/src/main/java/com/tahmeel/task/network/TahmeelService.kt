package com.tahmeel.task.network

import com.skydoves.sandwich.ApiResponse
import com.tahmeel.task.model.PendingOrdersResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface TahmeelService {

    @GET("technical_interview/orders/pending")
    suspend fun fetchPendingOrdersList(
        @Query("page") page: Int = 0
    ): ApiResponse<PendingOrdersResponse>
}
