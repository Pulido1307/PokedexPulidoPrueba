package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.resena

import com.antonio.pulido.pokedexpulido.domain.entidades.Usuario
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class AddResenaViewState(
    val successAdd: Boolean = false,

    val usuarios: List<Usuario> = listOf(),
    val usuario: String = "",

    val comentario: String = ""
): ViewState()
