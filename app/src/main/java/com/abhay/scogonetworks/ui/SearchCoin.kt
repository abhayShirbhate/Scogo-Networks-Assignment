package com.abhay.scogonetworks.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhay.scogonetworks.retrofit.model.CoinModel
import com.abhay.scogonetworks.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCoin(viewModel: MainViewModel) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredCoins = filterCoins(viewModel.coinList!!, searchQuery)

    Column {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newQuery ->
                searchQuery = newQuery
            },
            label = { Text("Search Coins") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        SwipeToRefresh(viewModel = viewModel,list = filteredCoins)
    }
}

fun filterCoins(coins: List<CoinModel>, query: String): List<CoinModel> {
    if (query == "") return  coins
    val lowercaseQuery = query.lowercase()
    return coins.filter { coin ->
        coin.name.lowercase().contains(lowercaseQuery) ||
                coin.symbol.lowercase().contains(lowercaseQuery)
    }
}

