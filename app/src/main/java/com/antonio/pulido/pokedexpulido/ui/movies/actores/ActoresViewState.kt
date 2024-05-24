package com.antonio.pulido.pokedexpulido.ui.movies.actores

import androidx.annotation.StringRes
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class ActoresViewState(
    val isLoading: Boolean = false,

    val showAddActor: Boolean = false,

    val name: String = "",
    @StringRes val nameError: Int? = null,

    val nacionalidad: String = "",
    @StringRes val nacionalidadError: Int? = null,

    val edad:String="",
    @StringRes val edadError: Int?=null,

    val actores: List<Actor> = listOf(),
    ): ViewState()

