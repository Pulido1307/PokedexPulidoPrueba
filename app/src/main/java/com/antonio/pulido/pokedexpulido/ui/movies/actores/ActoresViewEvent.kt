package com.antonio.pulido.pokedexpulido.ui.movies.actores

import com.antonio.pulido.pokedexpulido.domain.entidades.Actor

sealed interface ActoresViewEvent {
    object ShowDialogAddActor:  ActoresViewEvent
    object HiddenDialogAddActor:  ActoresViewEvent
    data class ShowDialogInfoActor(val item: Actor):  ActoresViewEvent
    object HiddenDialogInfoActor:  ActoresViewEvent
    object AddActor : ActoresViewEvent
    data class OnChangeName(val name: String): ActoresViewEvent
    data class OnChangeNacionalidad(val nacionalidad: String):ActoresViewEvent
    data class OnChangeEdad(val edad:String):ActoresViewEvent
}