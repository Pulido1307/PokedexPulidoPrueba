package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.resena

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericTextField
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens

@Composable
fun AddResenaScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddResenaViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<AddResenaViewState>().collectAsState()
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
        ) {
            GenericDropDown(
                options = uiState.usuarios.map { it.nombre ?: "" },
                selectedOption = uiState.usuario,
                onOptionSelected = { viewModel.onEvent(AddResenaViewEvent.OnChangeUsuario(it)) },
                label = "Usuario"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = uiState.comentario,
                onValueTextChange = { viewModel.onEvent(AddResenaViewEvent.OnChangeComentario(it)) },
                label = "Reseña"
            )
            Spacer(modifier = modifier.weight(1f))

            LargeCustomButton(text = "Agregar") {
                viewModel.onEvent(AddResenaViewEvent.AddResena(id))
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