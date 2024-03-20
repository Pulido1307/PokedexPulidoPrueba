package com.antonio.pulido.pokedexpulido.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.R
import com.antonio.pulido.pokedexpulido.ui.composables.TextFieldSearch
import com.antonio.pulido.pokedexpulido.ui.composables.dialogs.LoadingDialog
import com.antonio.pulido.pokedexpulido.ui.composables.nav.BottomNavigation
import com.antonio.pulido.pokedexpulido.ui.composables.scaffold.CustomScaffoldWithNav
import com.antonio.pulido.pokedexpulido.ui.main.composable.PokeCard
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens
import com.antonio.pulido.pokedexpulido.ui.theme.PrimaryCard
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by viewModel.getState<MainViewState>().collectAsState()

    LoadingDialog(isVisible = uiState.isLoading)
    CustomScaffoldWithNav(currentRoute = Screens.MAIN, navController = navController) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .fillMaxHeight(0.15f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
//                    .background(MaterialTheme.colorScheme.surface)
                    .background(PrimaryCard)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1f)
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .align(Alignment.Center)
                )

                IconButton(onClick = {
                    viewModel.onEvent(MainViewEvent.OnChageTheme)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.twotone_wb_sunny_24),
                        contentDescription = null,
                        modifier = modifier
                            .align(Alignment.TopEnd)
                            .size(30.dp)
                            .padding(top = 5.dp),
                        tint = Secondary
                    )
                }
            }

            Spacer(modifier = modifier.height(16.dp))

            Row {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(modifier = modifier.weight(1f)) {

                        TextFieldSearch(value = uiState.searchText ?: "", onValueTextChange = {
                            viewModel.onEvent(
                                MainViewEvent.OnSearchTextChange(it)
                            )
                        })
                    }
                    IconButton(onClick = {
//                        viewModel.onEvent(PropertiesViewEvent.ShowDialogFilter)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(modifier = modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(uiState.pokemonesFilter) {
                    val parts = it.url.split("/")

                    val id = parts[parts.size - 2].toInt()
                    PokeCard(id = id, nombre = it.name, onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "id", id ?: 0
                        )

                        navController.navigate(Screens.INFO)
                    })


                }
            }
        }
    }
}