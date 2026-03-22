package com.example.mad_cw_17914.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad_cw_17914.ui.screens.AddCatScreen
import com.example.mad_cw_17914.ui.screens.CatListScreen
import com.example.mad_cw_17914.ui.viewmodel.CatViewModel

@Composable
fun CatNavGraph(viewModel: CatViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "cat_list") {
        composable("cat_list") {
            // We will build this screen next [cite: 68]
            CatListScreen(viewModel, onNavigateToAdd = { navController.navigate("add_cat") })
        }
        composable("add_cat") {
            // We will build this screen next [cite: 70]
            AddCatScreen(viewModel, onBack = { navController.popBackStack() })
        }
    }
}