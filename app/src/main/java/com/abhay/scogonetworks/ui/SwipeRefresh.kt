package com.abhay.scogonetworks.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import com.abhay.scogonetworks.retrofit.model.CoinModel
import com.abhay.scogonetworks.viewmodel.MainViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.swiperefresh.SwipeRefresh

@Composable
fun SwipeToRefresh(viewModel: MainViewModel,list: List<CoinModel>) {
    val isRefreshing by remember { mutableStateOf(false) }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
           viewModel.getApiData()
        }
    ) {
        DetainListView(list = list)
    }
}