package com.antonio.pulido.pokedexpulido.ui.movies.actores

sealed interface ActoresViewEvent {
    object ShowDialogAddActor:  ActoresViewEvent
    object HiddenDialogAddActor:  ActoresViewEvent
    object AddActor : ActoresViewEvent
    data class OnChangeName(val name: String): ActoresViewEvent
    data class OnChangeNacionalidad(val nacionalidad: String):ActoresViewEvent
    data class OnChangeEdad(val edad:String):ActoresViewEvent
}