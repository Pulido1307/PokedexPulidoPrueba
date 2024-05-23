package com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.movies

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons.LargeCustomButton
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericDropDown
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovies(
    modifier: Modifier = Modifier,
    onDismissDialog: () -> Unit,
    name: String,
    @StringRes nameError: Int? = null,
    idioma: String,
    @StringRes idiomaError: Int? = null,
    descripcion: String,
    @StringRes descripcionError: Int? = null,
    duracion: String,
    @StringRes duracionError: Int? = null,
    year: String,
    @StringRes yearError: Int? = null,
    genero: String,
    @StringRes generoError: Int? = null,
    idiomas: List<String>,
    generos: List<String>,
    onNameChange: (String) -> Unit,
    onIdiomaChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onDuracionChange: (String) -> Unit,
    onYearChange: (String) -> Unit,
    onGeneroChange: (String) -> Unit,
    addMovie: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()


    ModalBottomSheet(
        onDismissRequest = onDismissDialog,
        //sheetMaxWidth = 100.dp,
        sheetState = modalBottomSheetState,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(0.dp, (LocalConfiguration.current.screenHeightDp * 0.7f).dp),
        containerColor = Color.White
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenericTextField(
                value = name,
                onValueTextChange = onNameChange,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                label = "Nombre de la película"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = descripcion,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                onValueTextChange = onDescripcionChange,
                label = "Descripción"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = duracion,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone,
                onValueTextChange = onDuracionChange,
                label = "Duración"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = year,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Phone,
                onValueTextChange = onYearChange,
                label = "Año"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericDropDown(
                options = idiomas,
                selectedOption = idioma,
                onOptionSelected = onIdiomaChange,
                label = "Idioma"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericDropDown(
                options = generos,
                selectedOption = genero,
                onOptionSelected = onGeneroChange,
                label = "Genero"
            )
            Spacer(modifier = modifier.height(16.dp))

            LargeCustomButton(text = "Agregar") {
                addMovie()
            }

            Spacer(modifier = modifier.height(16.dp))

        }
    }
}