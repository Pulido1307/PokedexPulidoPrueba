package com.antonio.pulido.pokedexpulido.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresScreen
import com.antonio.pulido.pokedexpulido.ui.movies.director.DirectorScreen
import com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.actuacion.AddActuacion
import com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.apps.AddAppsScreen
import com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.direcciones.AddDireccionesScreen
import com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.produccion.AddProduccionScreen
import com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.resena.AddResenaScreen
import com.antonio.pulido.pokedexpulido.ui.movies.movies.info.InfoMoviesScreen
import com.antonio.pulido.pokedexpulido.ui.movies.movies.list.MoviesScreen
import com.antonio.pulido.pokedexpulido.ui.movies.movies.update.UpdateMovieScreen
import com.antonio.pulido.pokedexpulido.ui.movies.productora.ProductoraScreen
import com.antonio.pulido.pokedexpulido.ui.movies.usuarios.UsuariosScreen
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
        
        composable(Screens.ACTORES_SCREEN){
            ActoresScreen(navController = navController)
        }

        composable(Screens.USUARIOS_SCREEN){
            UsuariosScreen(navController = navController)
        }
        
        composable(Screens.INFO_MOVIES_SCREEN){
            InfoMoviesScreen(navController = navController)
        }

        composable(Screens.PRODUCTORA_SCREEN){
            ProductoraScreen(navController = navController)
        }

        composable(Screens.DIRECTOR_SCREEN){
            DirectorScreen(navController = navController)
        }

        composable(Screens.UPDATE_MOVIES_SCREEN){
            UpdateMovieScreen(navController = navController)
        }
        composable(Screens.ADD_ACTUACION){
            AddActuacion(navController = navController)
        }
        composable(Screens.ADD_DIRECCIONES){
            AddDireccionesScreen(navController = navController)
        }
        composable(Screens.ADD_RESENA){
            AddResenaScreen(navController = navController)
        }
        composable(Screens.ADD_APP){
            AddAppsScreen(navController = navController)
        }
        composable(Screens.ADD_PRODUCCION){
            AddProduccionScreen(navController = navController)
        }
    }
}
