package com.antonio.pulido.pokedexpulido.repositories.local

import com.antonio.pulido.database.DbRoom
import com.antonio.pulido.domain.entities.PokeEntity
import com.antonio.pulido.domain.mappers.PokeMapper
import com.antonio.pulido.domain.models.response.info.PokeInfoResponse
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(
    private val database: DbRoom
) : LocalRepository{
    override suspend fun upsert(poke: PokeInfoResponse): Long {
        return database.pokeDao().upsert(PokeMapper.toEntity(poke))
    }

    override suspend fun getPokemon(): List<PokeEntity> {
        return database.pokeDao().getPokemones()
    }

    override suspend fun deletePokemonById(pokemonId: Int) {
        return database.pokeDao().deletePokemonById(pokemonId)
    }

    override suspend fun getPokemonById(pokemonId: Int): PokeEntity? {
        return database.pokeDao().getPokemonById(pokemonId)
    }
}