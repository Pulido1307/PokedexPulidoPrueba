package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

sealed interface InfoMoviesViewEvent {
    object ShowEditActor : InfoMoviesViewEvent
    object HiddenEditActor : InfoMoviesViewEvent
    object ShowEditDirector : InfoMoviesViewEvent
    object HiddenEditDirector : InfoMoviesViewEvent
    object ShowEditdProductor : InfoMoviesViewEvent
    object HiddenEditPrdoctor : InfoMoviesViewEvent
    object EditActor : InfoMoviesViewEvent
    object EditDirector : InfoMoviesViewEvent
    object EditProductor: InfoMoviesViewEvent
    object DeleteInfoMovie : InfoMoviesViewEvent
    data class GetInfo(val id: String) : InfoMoviesViewEvent
    data class OnChangeActorText(val text: String) : InfoMoviesViewEvent
    data class OnChangeDirectorText(val text: String) : InfoMoviesViewEvent
    data class OnChangeProductorText(val text: String) : InfoMoviesViewEvent
}