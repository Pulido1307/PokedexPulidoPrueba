package com.antonio.pulido.pokedexpulido.ui.movies.movies.update

sealed interface UpdateMovieViewEvent {

    object UpdateMovie : UpdateMovieViewEvent
    data class GetInfo(val id: String): UpdateMovieViewEvent
    data class OnChangeName(val name: String): UpdateMovieViewEvent
    data class OnChangeIdioma(val idioma: String): UpdateMovieViewEvent
    data class OnChangeDescripcion(val descripcion: String): UpdateMovieViewEvent
    data class OnChangeDuracion(val duracion: String): UpdateMovieViewEvent
    data class OnChangeYear(val year: String): UpdateMovieViewEvent
    data class OnChangeGenero(val genero: String): UpdateMovieViewEvent
}