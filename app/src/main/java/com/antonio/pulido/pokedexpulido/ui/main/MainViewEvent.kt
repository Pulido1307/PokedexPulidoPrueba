package com.antonio.pulido.pokedexpulido.ui.main

sealed interface MainViewEvent {
    data class onSearchTextChange(val search: String): MainViewEvent
}