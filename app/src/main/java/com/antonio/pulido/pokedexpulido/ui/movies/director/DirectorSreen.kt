package com.antonio.pulido.pokedexpulido.ui.movies.director

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewEvent
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewModel
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewState
import com.antonio.pulido.pokedexpulido.ui.movies.composables.cards.movies.CardMovies
import com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.actors.AddActores
import com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.director.AddDirector
import com.antonio.pulido.pokedexpulido.ui.movies.composables.scaffold.CustomScaffoldWithNavMovies
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens
import com.antonio.pulido.pokedexpulido.util.isNumeric

@Composable
fun DirectorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DirectorViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<DirectorViewState>().collectAsState()

    CustomScaffoldWithNavMovies(
        currentRoute = Screens.DIRECTOR_SCREEN,
        navController = navController,
        clickFloatingButton = { viewModel.onEvent(DirectorViewEvent.ShowDialogAddDirector) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 34.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(uiState.directores) {
                    AnimatedVisibility(visible = true) {
                        CardMovies(name = it.nombre ?: "") {

                        }
                    }
                }
            }
        }
    }

    when {
        uiState.showAddDirector -> {
            AddDirector(
                onDismissDialog = { viewModel.onEvent(DirectorViewEvent.HiddenDialogAddDirector) },
                nombre = uiState.name,
                edad = uiState.edad,
                onNombreChange = {
                    viewModel.onEvent(DirectorViewEvent.OnChangeName(it))
                },

                onEdadChange ={
                    if (it.isNumeric() || it.isEmpty())
                        viewModel.onEvent(DirectorViewEvent.OnChangeEdad(it))
                },
                addDirector = {
                    viewModel.onEvent(DirectorViewEvent.AddDirector)
                },

            )
        }
    }
}