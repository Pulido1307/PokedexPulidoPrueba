package com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.actors

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActores(
    modifier: Modifier = Modifier,
    onDismissDialog: () -> Unit,
    name: String,
    @StringRes nameError: Int? = null,
    nacionalidad: String,
    @StringRes nacionalidadError: Int? = null,
    edad: String,
    @StringRes edadError: Int? = null,
    onNameChange: (String) -> Unit,
    onNacionalidadChange: (String) -> Unit,
    onEdadChange: (String) -> Unit,
    addActor: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val sheetHeight = (screenHeight * 0.85f)

    ModalBottomSheet(
        onDismissRequest = onDismissDialog,
        sheetState = modalBottomSheetState,
        modifier = modifier
            .fillMaxWidth()
            .height(sheetHeight),
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
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                label = "Nombre del actor"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = edad,
                onValueTextChange = onEdadChange,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next,
                label = "Edad del actor"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = nacionalidad,
                onValueTextChange = onNacionalidadChange,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                label = "Nacionalidad del actor"
            )
            Spacer(modifier = modifier.height(16.dp))

            LargeCustomButton(text = "Agregar") {
                addActor()
            }
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}
