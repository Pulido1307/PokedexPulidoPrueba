package com.antonio.pulido.pokedexpulido.ui.movies.movies

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
import com.antonio.pulido.pokedexpulido.ui.composables.dialogs.LoadingDialog
import com.antonio.pulido.pokedexpulido.ui.movies.composables.cards.movies.CardMovies
import com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.movies.AddMovies
import com.antonio.pulido.pokedexpulido.ui.movies.composables.scaffold.CustomScaffoldWithNavMovies
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens
import com.antonio.pulido.pokedexpulido.util.isNumeric

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<MoviesViewState>().collectAsState()

    LoadingDialog(isVisible = uiState.isLoading)

    CustomScaffoldWithNavMovies(
        currentRoute = Screens.MOVIES_SCREEN,
        navController = navController,
        clickFloatingButton = {
            viewModel.onEvent(MoviesViewEvent.ShowDialogAddMovie)
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 34.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(uiState.peliculas) {
                    AnimatedVisibility(visible = true) {
                        CardMovies(name = it.nombre ?: "") {

                        }
                    }
                }
            }
        }
    }


    when {
        uiState.dialogAddMovie -> {
            AddMovies(
                onDismissDialog = { viewModel.onEvent(MoviesViewEvent.HiddenDialogAddMovie) },
                name = uiState.name,
                nameError = uiState.nameError,
                idioma = uiState.idioma,
                idiomaError = uiState.idomaError,
                descripcion = uiState.descripcion,
                descripcionError = uiState.descripcionError,
                duracion = uiState.duracion,
                duracionError = uiState.duracionError,
                year = uiState.year,
                yearError = uiState.yearError,
                genero = uiState.genero,
                generoError = uiState.generoError,
                idiomas = uiState.idiomas,
                generos = uiState.generos,
                onNameChange = {
                    viewModel.onEvent(MoviesViewEvent.OnChangeName(it))
                },
                onIdiomaChange = {
                    viewModel.onEvent(MoviesViewEvent.OnChangeIdioma(it))
                },
                onDescripcionChange = {
                    viewModel.onEvent(MoviesViewEvent.OnChangeDescripcion(it))
                },
                onDuracionChange = {
                    if (it.isNumeric() || it.isEmpty())
                        viewModel.onEvent(MoviesViewEvent.OnChangeDuracion(it))
                },
                onYearChange = {
                    if (it.isNumeric() || it.isEmpty())
                        viewModel.onEvent(MoviesViewEvent.OnChangeYear(it))
                },
                onGeneroChange = {
                    viewModel.onEvent(MoviesViewEvent.OnChangeGenero(it))
                },
                addMovie = {
                    viewModel.onEvent(MoviesViewEvent.AddMovie)
                }
            )
        }
    }
}