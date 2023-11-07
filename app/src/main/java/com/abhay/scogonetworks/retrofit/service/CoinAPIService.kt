package com.abhay.scogonetworks.retrofit.service

import com.abhay.scogonetworks.retrofit.model.CoinDetailsModel
import com.abhay.scogonetworks.retrofit.model.CoinModel
import com.abhay.scogonetworks.retrofit.model.CoinPriceModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CoinAPIService {
    @GET("coins")
    fun getCoins(): Call<List<CoinModel>>

    @GET("coins/{coinId}")
    fun getCoinsDetails(
        @Path("coinId") coinId: String
    ): Call<CoinDetailsModel>


    @GET("tickers/{coinId}")
    suspend fun getCoinPrice(@Path("coinId") coinId: String): CoinPriceModel
}