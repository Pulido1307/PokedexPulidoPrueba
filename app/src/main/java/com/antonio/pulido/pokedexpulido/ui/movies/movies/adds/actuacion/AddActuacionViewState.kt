package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.actuacion

import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class AddActuacionViewState(
    val successAdd: Boolean = false,
    val actores: List<Actor> = listOf(),
    val actor: String = "",
): ViewState()
