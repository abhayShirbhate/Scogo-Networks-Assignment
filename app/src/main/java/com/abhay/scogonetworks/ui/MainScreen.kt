package com.abhay.scogonetworks.ui

import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(coinList: List<CoinModel>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "coinList") {
        composable("coinList") {
            // Your coin list Composable
        }
        composable(
            "coinDetails/{coinId}",
            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
        ) { backStackEntry ->
            val coinId = backStackEntry.arguments?.getString("coinId")
            val coin = findCoinById(coinId, coinList) // Replace with your logic to find the coin by ID
            CoinDetailsScreen(coin)
        }
    }
}

