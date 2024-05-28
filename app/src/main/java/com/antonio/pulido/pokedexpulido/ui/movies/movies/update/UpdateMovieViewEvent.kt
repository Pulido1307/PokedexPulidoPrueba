package com.antonio.pulido.pokedexpulido.ui.movies.movies.update

import com.antonio.pulido.pokedexpulido.ui.movies.movies.list.MoviesViewEvent

sealed interface UpdateMovieViewEvent {
    object UpdateMovie : UpdateMovieViewEvent
    data class OnChangeName(val name: String): UpdateMovieViewEvent
    data class OnChangeIdioma(val idioma: String): UpdateMovieViewEvent
    data class OnChangeDescripcion(val descripcion: String): UpdateMovieViewEvent
    data class OnChangeDuracion(val duracion: String): UpdateMovieViewEvent
    data class OnChangeYear(val year: String): UpdateMovieViewEvent
    data class OnChangeGenero(val genero: String): UpdateMovieViewEvent
}