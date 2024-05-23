package com.antonio.pulido.pokedexpulido.ui.pokemon.main

import com.antonio.pulido.domain.models.response.list.PokeListResult
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class MainViewState(
    val isLoading: Boolean = false,
    val pokemones: List<PokeListResult> = listOf(),
    val pokemonesFilter: List<PokeListResult> = listOf(),
    val isDarkMode: Boolean = false,

    val searchText: String? = "",
): ViewState()
