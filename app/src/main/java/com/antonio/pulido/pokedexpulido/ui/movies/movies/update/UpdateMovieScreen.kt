package com.antonio.pulido.pokedexpulido.ui.movies.movies.update

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons.LargeCustomButton
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericDropDown
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericTextField
import com.antonio.pulido.pokedexpulido.util.isNumeric

@Composable
fun UpdateMovieScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: UpdateMovieViewModel = hiltViewModel()
) {
    val uiState by viewModel.getState<UpdateMovieViewState>().collectAsState()
    val id = navController.previousBackStackEntry?.savedStateHandle?.get<String>("id") ?: ""

    LaunchedEffect(viewModel) {
        viewModel.onEvent(UpdateMovieViewEvent.GetInfo(id))
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenericTextField(
                value = uiState.name,
                onValueTextChange = { viewModel.onEvent(UpdateMovieViewEvent.OnChangeName(it)) },
                label = "Nombre de la pelicula",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                supportingText = uiState.nameError
            )
            Spacer(modifier = modifier.height(16.dp))
            GenericTextField(
                value = uiState.descripcion,
                onValueTextChange = { viewModel.onEvent(UpdateMovieViewEvent.OnChangeDescripcion(it)) },
                label = "Descripción",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                supportingText = uiState.descripcionError
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = uiState.duracion,
                onValueTextChange = { viewModel.onEvent(UpdateMovieViewEvent.OnChangeDuracion(it)) },
                label = "Duracion",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                supportingText = uiState.duracionError
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = uiState.year,
                onValueTextChange = {
                    if (it.isNumeric() || it.isEmpty())
                        viewModel.onEvent(UpdateMovieViewEvent.OnChangeYear(it))
                },
                label = "Año",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.NumberPassword,
                supportingText = uiState.duracionError
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericDropDown(
                options = uiState.idiomas,
                selectedOption = uiState.idioma,
                onOptionSelected = {
                    viewModel.onEvent(UpdateMovieViewEvent.OnChangeIdioma(it))
                },
                label = "Idioma"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericDropDown(
                options = uiState.generos,
                selectedOption = uiState.genero,
                onOptionSelected = {
                    viewModel.onEvent(UpdateMovieViewEvent.OnChangeGenero(it))
                },
                label = "Genero"
            )
            Spacer(modifier = modifier.height(16.dp))

            LargeCustomButton(text = "Actualizar") {
                viewModel.onEvent(UpdateMovieViewEvent.UpdateMovie)
            }
        }
    }
}