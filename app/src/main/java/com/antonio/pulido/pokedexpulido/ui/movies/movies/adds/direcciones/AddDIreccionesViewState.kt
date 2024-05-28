package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.direcciones

import com.antonio.pulido.pokedexpulido.domain.entidades.Director
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class AddDIreccionesViewState(
    val isLoading: Boolean = false,
    val successAdd: Boolean = false,
    val directores: List<Director> = listOf(),
    val director: String = "",
) : ViewState()
