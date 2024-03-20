package com.antonio.pulido.pokedexpulido.ui.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.R
import com.antonio.pulido.pokedexpulido.ui.composables.TextFieldSearch
import com.antonio.pulido.pokedexpulido.ui.composables.dialogs.LoadingDialog
import com.antonio.pulido.pokedexpulido.ui.composables.scaffold.CustomScaffoldWithNav
import com.antonio.pulido.pokedexpulido.ui.main.MainViewEvent
import com.antonio.pulido.pokedexpulido.ui.main.MainViewState
import com.antonio.pulido.pokedexpulido.ui.main.composable.PokeCard
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by viewModel.getState<FavoritesViewState>().collectAsState()

    LoadingDialog(isVisible = uiState.isLoading)

    CustomScaffoldWithNav(currentRoute = Screens.FAVS, navController = navController) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Spacer(modifier = modifier.height(16.dp))
            TextFieldSearch(value = uiState.searchText ?: "", onValueTextChange = {
                viewModel.onEvent(
                    FavoritesViewEvent.OnSearchTextChange(it)
                )
            })

            Spacer(modifier = modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(uiState.pokefavsFilter) {

                    PokeCard(id = it.id, nombre = it.name, onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "id", it.id ?: 0
                        )

                        navController.navigate(Screens.INFO_LOCAL)
                    })
                }
            }
        }
    }

}