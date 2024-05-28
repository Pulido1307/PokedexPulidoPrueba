package com.antonio.pulido.pokedexpulido.ui.movies.director

import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Director
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewEvent

sealed interface DirectorViewEvent {
    object ShowDialogAddDirector: DirectorViewEvent
    object HiddenDialogAddDirector: DirectorViewEvent
    data class ShowDialogInfoDirector(val item: Director):  DirectorViewEvent
    object HiddenDialogInfoDirector:  DirectorViewEvent
    object AddDirector : DirectorViewEvent
    data class OnChangeName(val name: String): DirectorViewEvent
    data class OnChangeEdad(val edad:String): DirectorViewEvent
}