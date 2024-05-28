package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.direcciones

sealed interface AddDIreccionesViewEvent {
    data class AddDireccion(val id: String): AddDIreccionesViewEvent
    data class OnChangeDirector(val director: String): AddDIreccionesViewEvent
}