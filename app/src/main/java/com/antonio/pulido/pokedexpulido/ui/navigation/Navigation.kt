package com.antonio.pulido.pokedexpulido.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.antonio.pulido.pokedexpulido.ui.movies.movies.MoviesScreen
import com.antonio.pulido.pokedexpulido.ui.pokemon.favorites.FavoritesScreen
import com.antonio.pulido.pokedexpulido.ui.pokemon.info.local.InfoLocalScreen
import com.antonio.pulido.pokedexpulido.ui.pokemon.info.remote.InfoPokemonScreen
import com.antonio.pulido.pokedexpulido.ui.pokemon.main.MainScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.MOVIES_SCREEN
    ) {
        composable(Screens.MAIN) {
            MainScreen(navController = navController)
        }

        composable(Screens.INFO) {
            InfoPokemonScreen(navController = navController)
        }

        composable(Screens.FAVS) {
            FavoritesScreen(navController = navController)
        }

        composable(Screens.INFO_LOCAL) {
            InfoLocalScreen(navController = navController)
        }

        composable(Screens.MOVIES_SCREEN){
            MoviesScreen(navController = navController)
        }
    }
}
