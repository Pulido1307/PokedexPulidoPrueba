package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.direcciones

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
import com.antonio.pulido.pokedexpulido.ui.composables.dialogs.LoadingDialog
import com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons.LargeCustomButton
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericDropDown
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens

@Composable
fun AddDireccionesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddDIreccionesViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<AddDIreccionesViewState>().collectAsState()
    val id = navController.previousBackStackEntry?.savedStateHandle?.get<String>("id") ?: ""

    LoadingDialog(isVisible = uiState.isLoading)
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
                options = uiState.directores.map { it.nombre ?: "" },
                selectedOption = uiState.director,
                onOptionSelected = { viewModel.onEvent(AddDIreccionesViewEvent.OnChangeDirector(it)) },
                label = "Directores"
            )

            LargeCustomButton(text = "Agregar") {
                viewModel.onEvent(AddDIreccionesViewEvent.AddDireccion(id))
            }
        }
    }

    when {
        uiState.successAdd -> {
            navController.backQueue.clear()
            navController.navigate(Screens.MOVIES_SCREEN)
        }
    }
}