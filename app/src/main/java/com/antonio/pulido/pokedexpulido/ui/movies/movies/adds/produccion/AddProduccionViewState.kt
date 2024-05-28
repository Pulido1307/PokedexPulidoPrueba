package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.produccion

import com.antonio.pulido.pokedexpulido.domain.entidades.Productora
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class AddProduccionViewState(
    val successAdd: Boolean = false,
    val producciones: List<Productora> = listOf(),
    val produccion: String = ""
): ViewState()
