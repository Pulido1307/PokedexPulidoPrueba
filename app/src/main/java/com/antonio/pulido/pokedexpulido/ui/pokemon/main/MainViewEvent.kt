package com.antonio.pulido.pokedexpulido.ui.pokemon.main

sealed interface MainViewEvent {
    object OnChageTheme: MainViewEvent
    data class OnSearchTextChange(val search: String) : MainViewEvent
}