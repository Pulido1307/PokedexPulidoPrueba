package com.antonio.pulido.pokedexpulido.ui.movies.actores

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
import com.antonio.pulido.pokedexpulido.ui.movies.composables.cards.movies.CardMovies
import com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.actors.AddActores
import com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.actors.InfoActor
import com.antonio.pulido.pokedexpulido.ui.movies.composables.scaffold.CustomScaffoldWithNavMovies
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens
import com.antonio.pulido.pokedexpulido.util.isNumeric

@Composable
fun ActoresScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ActoresViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<ActoresViewState>().collectAsState()

    CustomScaffoldWithNavMovies(
        currentRoute = Screens.ACTORES_SCREEN,
        navController = navController,
        clickFloatingButton = { viewModel.onEvent(ActoresViewEvent.ShowDialogAddActor) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 34.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(uiState.actores) {
                    AnimatedVisibility(visible = true) {
                        CardMovies(name = it.nombre ?: "") {

                        }
                    }
                }
            }
        }
    }

    when {
        uiState.showAddActor -> {
            AddActores(
                onDismissDialog = { viewModel.onEvent(ActoresViewEvent.HiddenDialogAddActor) },
                name = uiState.name,
                nacionalidad = uiState.nacionalidad,
                edad = uiState.edad,
                onNameChange = {
                    viewModel.onEvent(ActoresViewEvent.OnChangeName(it))
                },
                onNacionalidadChange = {
                    viewModel.onEvent(ActoresViewEvent.OnChangeNacionalidad(it))
                },
                onEdadChange = {
                    if (it.isNumeric() || it.isEmpty())
                        viewModel.onEvent(ActoresViewEvent.OnChangeEdad(it))
                },
                addActor = {
                    viewModel.onEvent(ActoresViewEvent.AddActor)
                }
            )
        }

        uiState.showInfoActor -> {
            InfoActor(
                onDismissDialog = { viewModel.onEvent(ActoresViewEvent.HiddenDialogInfoActor) },
                nombre = uiState.name,
                nacionalidad = uiState.nacionalidad,
                edad = uiState.edad.toInt()
            )
        }
    }
}