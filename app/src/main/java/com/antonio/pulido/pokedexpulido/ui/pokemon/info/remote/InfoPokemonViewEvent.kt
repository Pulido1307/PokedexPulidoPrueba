package com.antonio.pulido.pokedexpulido.ui.pokemon.info.remote

sealed interface InfoPokemonViewEvent {
    object AddFavoritePokemon : InfoPokemonViewEvent
    object RemoveFavoritePokemon : InfoPokemonViewEvent
    data class GetPokeInfo(val id: Int) : InfoPokemonViewEvent
}