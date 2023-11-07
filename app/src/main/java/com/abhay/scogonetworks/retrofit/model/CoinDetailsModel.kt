package com.abhay.scogonetworks.retrofit.model

import com.google.gson.annotations.SerializedName


data class CoinDetailsModel(
    val name: String,
    val symbol: String,
    val logo: String,
    val description: String,
    @SerializedName("started_at")
    val startedAt: String,
    @SerializedName("open_source")
    val openSource: Boolean
)