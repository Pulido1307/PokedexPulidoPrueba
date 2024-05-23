package com.antonio.pulido.pokedexpulido.ui.pokemon.info.remote

import com.antonio.pulido.domain.models.response.info.PokeInfoResponse
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class InfoPokemonViewState(
    val isLoading: Boolean = false,
    val pokeInfo: PokeInfoResponse = PokeInfoResponse(),
    val isFav: Boolean = false
): ViewState()

