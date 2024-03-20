package com.antonio.pulido.pokedexpulido.repositories.pokemon.local

import com.antonio.pulido.domain.entities.PokeEntity
import com.antonio.pulido.domain.models.response.info.PokeInfoResponse

interface LocalRepository {
    suspend fun upsert(pokeInfoResponse: PokeInfoResponse): Long
    suspend fun getPokemon(): List<PokeEntity>
    suspend fun deletePokemonById(pokemonId: Int)
    suspend fun getPokemonById(pokemonId: Int): PokeEntity?
}