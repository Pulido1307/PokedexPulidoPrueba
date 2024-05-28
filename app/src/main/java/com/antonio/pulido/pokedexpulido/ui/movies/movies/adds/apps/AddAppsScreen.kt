package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.apps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons.LargeCustomButton
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericDropDown
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens

@Composable
fun AddAppsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddAppsViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<AddAppsViewState>().collectAsState()
    val id = navController.previousBackStackEntry?.savedStateHandle?.get<String>("id") ?: ""

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GenericDropDown(
                options = uiState.aplicaciones.map { it.nombre?:"" },
                selectedOption = uiState.app,
                onOptionSelected = {viewModel.onEvent(AddAppsViewEvent.OnChangeText(it))},
                label = "Aplicación"
            )

            LargeCustomButton(text = "Agregar") {
                viewModel.onEvent(AddAppsViewEvent.AddApp(id))
            }
        }
    }
    when{
        uiState.successAdd->{
            navController.backQueue.clear()
            navController.navigate(Screens.MOVIES_SCREEN)
        }
    }
}