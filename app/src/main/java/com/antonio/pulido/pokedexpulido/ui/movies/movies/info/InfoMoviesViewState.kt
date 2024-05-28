package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Director
import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
import com.antonio.pulido.pokedexpulido.domain.entidades.Productora
import com.antonio.pulido.pokedexpulido.viewstate.ViewState
import com.tec.crudbasededatos.domain.models.pivotes.Producir
import com.tec.crudbasededatos.domain.models.pivotes.Resenar
import com.tec.crudbasededatos.domain.models.pivotes.Ver

data class InfoMoviesViewState(
    val isLoading: Boolean = false,
    val id: String = "",
    val successDelete: Boolean = false,

    val pelicula: Pelicula = Pelicula(),

    val actores: List<Actor> = listOf(),
    val directores: List<Director> = listOf(),
    val produtores: List<Productora> = listOf(),
    val aplicaciones: List<Ver> = listOf(),
    val producir: List<Producir> = listOf(),

    val resenas: List<Resenar> = listOf(),

    val actoresInvolucrados: List<Actor> = listOf(),
    val directoresInvolucrados: List<Director> = listOf(),
    val produccionesInvolucradas: List<Productora> = listOf(),

    val actorSeleccionado: String = "",
    val showEditActor: Boolean = false,

    val directorSeleccionado: String = "",
    val showEditDirector: Boolean = false,

    val productorSeleccionado: String = "",
    val showEditProductora: Boolean = false,

    val dirigir: List<Director> = listOf(),
    val producidas: List<Productora> = listOf(),

): ViewState()
