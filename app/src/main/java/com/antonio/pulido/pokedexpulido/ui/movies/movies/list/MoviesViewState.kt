package com.antonio.pulido.pokedexpulido.ui.movies.movies.list

import androidx.annotation.StringRes
import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class MoviesViewState(
    val isLoading: Boolean = false,

    val dialogAddMovie: Boolean = false,

    val name: String = "",
    @StringRes val nameError: Int? = null,

    val idioma: String = "",
    @StringRes val idomaError: Int? = null,

    val descripcion: String = "",
    @StringRes val descripcionError: Int? = null,

    val duracion: String = "",
    @StringRes val duracionError: Int? = null,

    val year: String = "",
    @StringRes val yearError: Int? = null,

    val genero: String = "",
    @StringRes val generoError: Int? = null,

    val idiomas: List<String> = listOf(
        "Español",
        "Ingles",
    ),

    val generos: List<String> = listOf(
        "Comedia",
        "Romance",
    ),

    val peliculas: List<Pelicula> = listOf(),
): ViewState()
