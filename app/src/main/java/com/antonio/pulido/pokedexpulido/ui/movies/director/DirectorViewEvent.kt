package com.antonio.pulido.pokedexpulido.ui.movies.director

import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewEvent

sealed interface DirectorViewEvent {
    object ShowDialogAddDirector: DirectorViewEvent
    object HiddenDialogAddDirector: DirectorViewEvent
    object AddDirector : DirectorViewEvent
    data class OnChangeName(val name: String): DirectorViewEvent
    data class OnChangeEdad(val edad:String): DirectorViewEvent
}