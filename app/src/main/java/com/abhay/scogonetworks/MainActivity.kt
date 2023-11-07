package com.abhay.scogonetworks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.abhay.scogonetworks.repository.RepositoryImpl
import com.abhay.scogonetworks.retrofit.retrofitClient.RetrofitClient
import com.abhay.scogonetworks.ui.SearchCoin
import com.abhay.scogonetworks.ui.SwipeToRefresh
import com.abhay.scogonetworks.ui.theme.ScogoNetworksTheme
import com.abhay.scogonetworks.viewmodel.MainViewModel
import com.abhay.scogonetworks.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        setContent {
            ScogoNetworksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isLoading by viewModel.isDataLoading.collectAsState()

                    if (isLoading){
                        LoadingProgressBar()
                    }else if (viewModel.coinList != null) {
                        SearchCoin(viewModel = viewModel)
                    }else{
                        ErrorScreen(errorText = viewModel.errorText)
                    }
                }
            }
        }
    }

    private fun initViewModel(){
        val nasaApiClient = RetrofitClient()
        val repository = RepositoryImpl(nasaApiClient.apiService)
        val mainViewsFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,mainViewsFactory)[MainViewModel::class.java]
        viewModel.getApiData()

    }
}



@Composable
fun LoadingProgressBar() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = Color.Green,
            strokeWidth = Dp(value = 4F)
        )
    }
}

@Composable
fun ErrorScreen(errorText: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorText,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ScogoNetworksTheme {
//        DetainListView(list = )
//    }
//}