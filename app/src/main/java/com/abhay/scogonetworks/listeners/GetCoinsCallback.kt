package com.abhay.scogonetworks.listeners

import com.abhay.scogonetworks.retrofit.model.CoinModel

interface GetCoinsCallback {
    fun onApiCallSuccessResponse(response:List<CoinModel>)
    fun onApiCallFailureResponse(errorText:String)
}