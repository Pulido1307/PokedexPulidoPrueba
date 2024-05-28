package com.antonio.pulido.pokedexpulido.ui.movies.usuarios

import androidx.annotation.StringRes
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Usuario
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class UsuariosViewState(
    val isLoading: Boolean = false,

    val showAddAUsuario: Boolean = false,
    val showInfoUsuario: Boolean = false,

    val name: String = "",
    @StringRes val nameError: Int? = null,

    val pais: String = "",
    @StringRes val paisError: Int? = null,

    val genero:String="",
    @StringRes val generoError: Int?=null,

    val usuarios: List<Usuario> = listOf(),

    val usuarioSeleccionado: Usuario = Usuario()
): ViewState()
