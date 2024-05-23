package com.antonio.pulido.pokedexpulido.ui.pokemon.info.local

import com.antonio.pulido.domain.entities.PokeEntity
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class InfoLocalViewState(
    val pokemon: PokeEntity = PokeEntity(),
    val isFav: Boolean = false,
    val type: List<String> = listOf()
): ViewState()
