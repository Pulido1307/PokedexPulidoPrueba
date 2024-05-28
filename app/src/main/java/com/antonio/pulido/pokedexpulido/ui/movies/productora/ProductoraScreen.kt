package com.antonio.pulido.pokedexpulido.ui.movies.productora

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
import com.antonio.pulido.pokedexpulido.ui.movies.composables.cards.movies.CardMovies
import com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.actors.InfoActor
import com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.productora.AddProductora
import com.antonio.pulido.pokedexpulido.ui.movies.composables.scaffold.CustomScaffoldWithNavMovies
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens

@Composable
fun ProductoraScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProductoraViewModel = hiltViewModel()
)
{
    val uiState by viewModel.getState<ProductoraViewState>().collectAsState()

    CustomScaffoldWithNavMovies(
        currentRoute = Screens.PRODUCTORA_SCREEN,
        navController = navController,
        clickFloatingButton = { viewModel.onEvent(ProductoraViewEvent.ShowDialogAddProductora) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 34.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(uiState.productora) {
                    AnimatedVisibility(visible = true) {
                        CardMovies(name = it.nombre ?: "") {

                        }
                    }
                }
            }
        }

        when {
            uiState.showAddProductora -> {
                AddProductora(
                    onDismissDialog = { viewModel.onEvent(ProductoraViewEvent.HiddenDialogAddProductora) },
                    nombre = uiState.name,
                    pais = uiState.pais,
                    onNombreChange = {
                        viewModel.onEvent(ProductoraViewEvent.OnChangeName(it))
                    },
                    onPaisChange = {
                        viewModel.onEvent(ProductoraViewEvent.OnChangePais(it))
                    },
                    addProductora = {
                        viewModel.onEvent(ProductoraViewEvent.AddProductora)
                    }
                )
            }
            uiState.showInfoProductora -> {
                InfoProductora(
                    onDismissDialog = { viewModel.onEvent(ProductoraViewEvent.HiddenDialogInfoProductora) },
                    nombre = uiState.productoraSeleccionada.nombre?:"",
                    pais = uiState.productoraSeleccionada.pais?:"",
                )
            }
        }
    }
}