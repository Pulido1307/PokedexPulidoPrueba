package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.produccion

sealed interface AddProduccionViewEvent {
    data class AddProduccion(val id: String) : AddProduccionViewEvent
    data class OnChangeText(val text: String) : AddProduccionViewEvent
}