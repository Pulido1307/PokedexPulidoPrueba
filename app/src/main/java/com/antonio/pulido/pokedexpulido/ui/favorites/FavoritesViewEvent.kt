package com.antonio.pulido.pokedexpulido.ui.favorites

import com.antonio.pulido.pokedexpulido.ui.main.MainViewEvent

sealed interface FavoritesViewEvent {
    data class OnSearchTextChange(val search: String): FavoritesViewEvent

}