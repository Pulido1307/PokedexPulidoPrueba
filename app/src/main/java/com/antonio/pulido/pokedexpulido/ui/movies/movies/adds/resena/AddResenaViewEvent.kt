package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.resena

sealed interface AddResenaViewEvent {
    data class AddResena(val id: String) : AddResenaViewEvent
    data class OnChangeUsuario(val usuario: String) : AddResenaViewEvent
    data class OnChangeComentario(val comentario: String):AddResenaViewEvent
}