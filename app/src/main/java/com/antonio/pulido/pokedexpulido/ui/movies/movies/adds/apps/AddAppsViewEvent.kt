package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.apps

sealed interface AddAppsViewEvent {
    data class AddApp(val id: String) : AddAppsViewEvent
    data class OnChangeText(val text: String) : AddAppsViewEvent
}