package com.tahmeel.task.network

import com.skydoves.sandwich.ApiResponse
import com.tahmeel.task.model.PendingOrdersResponse
import javax.inject.Inject


class TahmeelClient @Inject constructor(
    private val tahmeelService: TahmeelService
) {

    suspend fun fetchPendingOrdersList(
        page: Int
    ): ApiResponse<PendingOrdersResponse> = tahmeelService.fetchPendingOrdersList(page)

}
