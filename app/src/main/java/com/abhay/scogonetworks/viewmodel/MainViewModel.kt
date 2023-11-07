package com.abhay.scogonetworks.viewmodel

import android.content.Context
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhay.scogonetworks.listeners.GetCoinsCallback
import com.abhay.scogonetworks.repository.Repository
import com.abhay.scogonetworks.retrofit.model.CoinModel
import com.abhay.scogonetworks.listeners.IOnMainActivityHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val repository: Repository): ViewModel(),
    IOnMainActivityHandler, GetCoinsCallback {


    var coinList : List<CoinModel>? = null
    var errorText : String = "SomeThing went wrong"


    private val _isDataLoading = MutableStateFlow(true)
    val isDataLoading: StateFlow<Boolean> = _isDataLoading

    fun getApiData(){
        _isDataLoading.value = true
        repository.getCoins(this)
    }






    override fun onApiCallSuccessResponse(response: List<CoinModel>) {
        _isDataLoading.value = false
        coinList = response
    }

    override fun onApiCallFailureResponse(errorText: String) {
        _isDataLoading.value = false
        this.errorText = errorText
    }

}