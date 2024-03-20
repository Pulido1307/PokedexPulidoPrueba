package com.antonio.pulido.pokedexpulido.ui.favorites

import com.antonio.pulido.domain.entities.PokeEntity
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class FavoritesViewState(
    val isLoading: Boolean = false,
    val pokefavs: List<PokeEntity> = listOf(),
    val pokefavsFilter: List<PokeEntity> = listOf(),
    val searchText: String? = "",
) : ViewState()
