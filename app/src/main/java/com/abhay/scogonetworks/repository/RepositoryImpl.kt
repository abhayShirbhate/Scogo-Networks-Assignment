package com.abhay.scogonetworks.repository

import com.abhay.scogonetworks.listeners.GetCoinsCallback
import com.abhay.scogonetworks.retrofit.model.CoinModel
import com.abhay.scogonetworks.retrofit.service.CoinAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RepositoryImpl(private val apiService: CoinAPIService): Repository {

    override fun getCoins(callback: GetCoinsCallback) {
        apiService.getCoins()
            .enqueue(object : Callback<List<CoinModel>?> {
                override fun onResponse(
                    call: Call<List<CoinModel>?>,
                    response: Response<List<CoinModel>?>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onApiCallSuccessResponse(response.body()!!)
                    } else {
                        callback.onApiCallFailureResponse(response.message())
                    }
                }

                override fun onFailure(call: Call<List<CoinModel>?>, t: Throwable) {
                    if (t is SocketTimeoutException || t is UnknownHostException){
                        callback.onApiCallFailureResponse("Please check your internet connection and try again")
                    }else callback.onApiCallFailureResponse(t.message?:"Something went wrong")
                }
            })
    }

}