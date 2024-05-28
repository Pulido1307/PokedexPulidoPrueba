package com.antonio.pulido.pokedexpulido.ui.movies.movies.update

import androidx.annotation.StringRes
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class UpdateMovieViewState(
    val isLoading: Boolean = false,
    val successUpdate: Boolean = false,

    val codigo: String = "",

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
        "Francés",
        "Alemán",
        "Italiano",
        "Portugués",
        "Ruso",
        "Japonés",
        "Coreano",
        "Árabe",
        "Hindi",
        "Turco",
        "Griego",
        "Hebreo",
        "Holandés",
        "Sueco",
        "Polaco",
    ),

    val generos: List<String> = listOf(
        "Comedia",
        "Romance",
        "Drama",
        "Acción",
        "Terror",
        "Ciencia Ficción",
        "Fantasía",
        "Suspenso",
        "Aventura",
        "Musical",
        "Animación",
        "Familia",
        "Histórico",
        "Bélico",
        "Deportes",
        "Crimen",
        "Misterio",
    ),
): ViewState()
