package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.produccion

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
fun AddProduccionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddProduccionViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<AddProduccionViewState>().collectAsState()
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
                options = uiState.producciones.map { it.nombre?:"" },
                selectedOption = uiState.produccion,
                onOptionSelected = {viewModel.onEvent(AddProduccionViewEvent.OnChangeText(it))},
                label = "Producciones"
            )

            LargeCustomButton(text = "Agregar") {
                viewModel.onEvent(AddProduccionViewEvent.AddProduccion(id))
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