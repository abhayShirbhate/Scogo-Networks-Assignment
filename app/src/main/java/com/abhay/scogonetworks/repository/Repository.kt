package com.abhay.scogonetworks.repository

import com.abhay.scogonetworks.listeners.GetCoinsCallback

interface Repository {
    fun getCoins(callback: GetCoinsCallback)
}