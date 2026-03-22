package com.example.mad_cw_17914

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mad_cw_17914.data.network.RetrofitClient
import com.example.mad_cw_17914.data.repository.CatRepository
import com.example.mad_cw_17914.ui.navigation.CatNavGraph
import com.example.mad_cw_17914.ui.theme.MAD_CW_17914Theme
import com.example.mad_cw_17914.ui.viewmodel.CatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize the Data Layer (Network and Repository)
        val apiService = RetrofitClient.instance
        val repository = CatRepository(apiService)

        // 2. Initialize the UI Layer State Holder (ViewModel)
        val viewModel = CatViewModel(repository)

        enableEdgeToEdge()
        setContent {
            MAD_CW_17914Theme {
                // Surface provides the background color from your theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 3. Launch the Navigation Graph to handle screen switching
                    CatNavGraph(viewModel = viewModel)
                }
            }
        }
    }
}