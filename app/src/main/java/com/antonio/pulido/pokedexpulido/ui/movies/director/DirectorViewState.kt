package com.antonio.pulido.pokedexpulido.ui.movies.director

import androidx.annotation.StringRes
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Director
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class DirectorViewState(
    val isLoading: Boolean = false,

    val showAddDirector: Boolean = false,

    val name: String = "",
    @StringRes val nameError: Int? = null,

    val edad:String="",
    @StringRes val edadError: Int?=null,

    val directores: List<Director> = listOf(),
): ViewState()
