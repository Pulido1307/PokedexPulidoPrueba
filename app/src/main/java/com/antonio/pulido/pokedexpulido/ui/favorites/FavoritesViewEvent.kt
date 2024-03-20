package com.antonio.pulido.pokedexpulido.ui.favorites


sealed interface FavoritesViewEvent {
    data class OnSearchTextChange(val search: String): FavoritesViewEvent

}