package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class InfoMoviesViewState(
    val isLoading: Boolean = false,

    val pelicula: Pelicula = Pelicula(),
): ViewState()
