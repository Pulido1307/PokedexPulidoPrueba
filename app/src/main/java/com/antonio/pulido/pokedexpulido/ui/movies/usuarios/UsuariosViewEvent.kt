package com.antonio.pulido.pokedexpulido.ui.movies.usuarios

import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Usuario
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewEvent

sealed interface UsuariosViewEvent {
    object  ShowDialogAddUsuario: UsuariosViewEvent
    object HiddenDialogAddUsuario: UsuariosViewEvent
    data class ShowDialogInfoUsuario(val item: Usuario): UsuariosViewEvent
    object HiddenDialogInfoUsuario: UsuariosViewEvent
    object addUsuario : UsuariosViewEvent
    data class onChangeNombre(val nombre: String):UsuariosViewEvent
    data class onChangePais(val pais: String):UsuariosViewEvent
    data class onChangeGenero(val genero: String):UsuariosViewEvent
}