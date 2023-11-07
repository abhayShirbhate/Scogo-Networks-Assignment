package com.abhay.scogonetworks.retrofit.model

import com.google.gson.annotations.SerializedName


data class CoinModel(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isNew: Boolean,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("type")
    val type: String
)
