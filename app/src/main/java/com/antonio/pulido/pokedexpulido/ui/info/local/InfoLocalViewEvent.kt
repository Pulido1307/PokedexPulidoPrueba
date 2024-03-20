package com.antonio.pulido.pokedexpulido.ui.info.local

import com.antonio.pulido.pokedexpulido.ui.info.remote.InfoPokemonViewEvent

sealed interface InfoLocalViewEvent {
    data class GetPokeInfo(val id: Int) : InfoLocalViewEvent
}