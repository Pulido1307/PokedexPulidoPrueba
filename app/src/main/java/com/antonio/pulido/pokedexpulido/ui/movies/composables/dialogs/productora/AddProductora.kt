package com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.productora

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons.LargeCustomButton
import com.antonio.pulido.pokedexpulido.ui.movies.composables.textfields.GenericTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductora(
    modifier: Modifier = Modifier,
    onDismissDialog: () -> Unit,
    nombre: String,
    pais: String,
    onNombreChange: (String) -> Unit,
    onPaisChange: (String) -> Unit,
    addProductora: () -> Unit

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
                value = nombre,
                onValueTextChange = onNombreChange,
                keyboardType = KeyboardType.Text,
                label = "Nombre de la productora"
            )
            Spacer(modifier = modifier.height(16.dp))

            GenericTextField(
                value = pais,
                onValueTextChange = onPaisChange,
                keyboardType = KeyboardType.Text,
                label = "País",
            )
            Spacer(modifier = modifier.height(16.dp))


            Spacer(modifier = modifier.height(16.dp))
            LargeCustomButton(text = "Agregar") {
                addProductora()
            }

            Spacer(modifier = modifier.height(16.dp))
        }
    }
}