package com.antonio.pulido.pokedexpulido.ui.movies.movies

sealed interface MoviesViewEvent {
    object ShowDialogAddMovie : MoviesViewEvent
    object HiddenDialogAddMovie : MoviesViewEvent
    object AddMovie : MoviesViewEvent
    data class OnChangeName(val name: String): MoviesViewEvent
    data class OnChangeIdioma(val idioma: String): MoviesViewEvent
    data class OnChangeDescripcion(val descripcion: String): MoviesViewEvent
    data class OnChangeDuracion(val duracion: String): MoviesViewEvent
    data class OnChangeYear(val year: String): MoviesViewEvent
    data class OnChangeGenero(val genero: String): MoviesViewEvent
}