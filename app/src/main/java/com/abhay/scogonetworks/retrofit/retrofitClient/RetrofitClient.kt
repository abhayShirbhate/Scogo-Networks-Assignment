package com.abhay.scogonetworks.retrofit.retrofitClient


import com.abhay.scogonetworks.retrofit.service.CoinAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    val apiService: CoinAPIService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(CoinAPIService::class.java)
    }



    companion object {
        private const val BASE_URL = "https://api.coinpaprika.com/v1/"
    }
}
