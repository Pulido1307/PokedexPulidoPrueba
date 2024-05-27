package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

sealed interface InfoMoviesViewEvent {
    object ShowEditActor : InfoMoviesViewEvent
    object HiddenEditActor : InfoMoviesViewEvent
    object EditActor : InfoMoviesViewEvent
    object DeleteInfoMovie : InfoMoviesViewEvent
    data class GetInfo(val id: String) : InfoMoviesViewEvent
    data class OnChangeActorText(val text: String) : InfoMoviesViewEvent
}