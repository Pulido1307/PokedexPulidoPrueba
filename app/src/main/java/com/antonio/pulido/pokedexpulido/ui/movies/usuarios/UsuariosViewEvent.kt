package com.antonio.pulido.pokedexpulido.ui.movies.usuarios

sealed interface UsuariosViewEvent {
    object  ShowDialogAddUsuario: UsuariosViewEvent
    object HiddenDialogAddUsuario: UsuariosViewEvent
    object addUsuario : UsuariosViewEvent
    data class onChangeNombre(val nombre: String):UsuariosViewEvent
    data class onChangePais(val pais: String):UsuariosViewEvent
    data class onChangeGenero(val genero: String):UsuariosViewEvent
}