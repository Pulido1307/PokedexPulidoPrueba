package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.apps

import com.antonio.pulido.pokedexpulido.domain.entidades.Aplicacion
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class AddAppsViewState(
    val successAdd: Boolean = false,
    val aplicaciones: List<Aplicacion> = listOf(),
    val app: String = "",
) : ViewState()
