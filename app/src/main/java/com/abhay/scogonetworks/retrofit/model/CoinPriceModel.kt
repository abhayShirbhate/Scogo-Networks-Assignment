package com.abhay.scogonetworks.retrofit.model

import com.google.gson.annotations.SerializedName


data class CoinPriceModel(
    val id: String,
    val quotes: Quotes
) {
    data class Quotes(
        @SerializedName("USD")
        val usd: Usd
    )

    data class Usd(
        val price: Double
    )
}