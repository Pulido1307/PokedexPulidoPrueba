package com.antonio.pulido.pokedexpulido.ui.main

import com.antonio.pulido.domain.models.response.PokeListResult
import com.antonio.pulido.domain.models.response.PokeResponse
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class MainViewState(
    val isLoading: Boolean = false,
    val pokemones: List<PokeListResult> = listOf(),
    val pokemonesFilter: List<PokeListResult> = listOf(),

    val searchText: String? = "",
): ViewState()
