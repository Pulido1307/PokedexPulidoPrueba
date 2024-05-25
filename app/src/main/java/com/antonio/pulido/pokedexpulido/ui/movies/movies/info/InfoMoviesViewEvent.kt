package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

sealed interface InfoMoviesViewEvent {
    data class GetInfo(val id: String): InfoMoviesViewEvent
}