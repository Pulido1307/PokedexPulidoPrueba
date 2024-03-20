package com.antonio.pulido.pokedexpulido.ui.main

sealed interface MainViewEvent {
    object OnChageTheme: MainViewEvent
    data class OnSearchTextChange(val search: String) : MainViewEvent
}