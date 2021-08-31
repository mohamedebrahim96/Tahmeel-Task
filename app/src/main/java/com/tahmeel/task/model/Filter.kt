package com.tahmeel.task.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by @mohamedebrahim96 on 20,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

@JsonClass(generateAdapter = true)
data class Filter(
    @field:Json(name = "page") var page: Int,
    @field:Json(name = "filter_by_cent") var filterByCent: Int?
)