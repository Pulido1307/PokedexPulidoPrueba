package com.antonio.pulido.pokedexpulido.ui.pokemon.favorites


sealed interface FavoritesViewEvent {
    data class OnSearchTextChange(val search: String): FavoritesViewEvent

}