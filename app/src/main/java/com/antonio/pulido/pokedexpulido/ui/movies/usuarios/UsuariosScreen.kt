package com.antonio.pulido.pokedexpulido.ui.movies.usuarios

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
import com.antonio.pulido.pokedexpulido.ui.movies.usuarios.UsuariosViewEvent
import com.antonio.pulido.pokedexpulido.ui.movies.composables.cards.movies.CardMovies
import com.antonio.pulido.pokedexpulido.ui.movies.composables.scaffold.CustomScaffoldWithNavMovies
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens


@Composable
fun UsuariosScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: UsuariosViewModel = hiltViewModel()
){

val uiState by viewModel.getState<UsuariosViewState>().collectAsState()

CustomScaffoldWithNavMovies(
currentRoute = Screens.USUARIOS_SCREEN,
navController = navController,
clickFloatingButton = { viewModel.onEvent(UsuariosViewEvent.ShowDialogAddUsuario) }
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 34.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(uiState.usuarios) {
                AnimatedVisibility(visible = true) {
                    CardMovies(name = it.nombre ?: "") {

                    }
                }
            }
        }
    }

    when {
        uiState.showAddAUsuario -> {
            AddUsuarios(
                onDismissDialog = { viewModel.onEvent(UsuariosViewEvent.HiddenDialogAddAUsuario) },
                name = uiState.name,
                pais = uiState.pais,
                genero = uiState.genero,
                onNameChange = {
                    viewModel.onEvent(UsuariosViewEvent.OnChangeName(it))
                },
                onPaisChange = {
                    viewModel.onEvent(UsuariosViewEvent.OnChangePais(it))
                },
                onGeneroChange ={
                    viewModel.onEvent(UsuariosViewEvent.OnChangeGenero(it))

                },
                addUsuario = {
                    viewModel.onEvent(UsuariosViewEvent.AddUsuario)
                }
            )
        }
    }
}
}