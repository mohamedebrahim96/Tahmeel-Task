package com.tahmeel.task.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by @mohamedebrahim96 on 16,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

@JsonClass(generateAdapter = true)
data class PendingOrdersResponse(
    @field:Json(name = "page") val page: Int?,
    @field:Json(name = "pending_orders") val pendingOrders: List<Order>
)