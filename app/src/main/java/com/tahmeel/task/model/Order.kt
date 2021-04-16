package com.tahmeel.task.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by @mohamedebrahim96 on 16,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */


@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Order(
    @field:Json(name = "load_ref") @PrimaryKey val loadRef: Int?,
    @field:Json(name = "created_at") val createdAt: String?,
    @field:Json(name = "customer_name") val customerName: String?,
    @field:Json(name = "order_number") val orderNumber: String?,
    @field:Json(name = "order_price_formatted") val orderPriceFormatted: String?,
    @field:Json(name = "phone_number") val phoneNumber: String?,
    @field:Json(name = "tahmeel_fee_in_cents") val tahmeelFeeInCents: Int?,
) : Parcelable

//"created_at": "2021-02-22T13:38:23.071910Z",
//"customer_name": "papryk",
//"load_ref": "18689",
//"order_number": "46fa5cb5-751d-4e77-9030-218a837c6dc2",
//"order_price_formatted": "2.00 AED",
//"phone_number": "123",
//"tahmeel_fee_in_cents": 2000