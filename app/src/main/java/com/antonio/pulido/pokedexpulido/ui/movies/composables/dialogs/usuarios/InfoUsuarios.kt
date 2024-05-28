package com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.usuarios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons.LargeCustomButton
import com.antonio.pulido.pokedexpulido.ui.movies.movies.info.CellInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoActor(
    modifier: Modifier = Modifier,
    onDismissDialog: () -> Unit,
    nombre: String,
    pais: String,
    genero: String
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
            CellInfo(title = "Nombre del usuario", info = nombre)
            CellInfo(title = "Pais", info = pais)
            CellInfo(title = "Genero", info = genero)
            LargeCustomButton(text = "Editar") {

            }
        }
    }
}