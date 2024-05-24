package com.antonio.pulido.pokedexpulido.ui.movies.composables.dialogs.actors

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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

) {

}