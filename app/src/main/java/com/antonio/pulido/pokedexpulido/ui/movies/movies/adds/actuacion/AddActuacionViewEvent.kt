package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.actuacion

sealed interface AddActuacionViewEvent {
    data class AddActuacion(val id: String) : AddActuacionViewEvent
    data class OnChangeActor(val actor: String) : AddActuacionViewEvent
}