package com.antonio.pulido.pokedexpulido.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.antonio.pulido.pokedexpulido.ui.main.MainScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.MAIN
    ) {
        composable(Screens.MAIN) {
            MainScreen(navController = navController)
        }
    }
}
