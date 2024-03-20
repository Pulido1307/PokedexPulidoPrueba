package com.antonio.pulido.pokedexpulido.ui.info.local

sealed interface InfoLocalViewEvent {
    data class GetPokeInfo(val id: Int) : InfoLocalViewEvent
}