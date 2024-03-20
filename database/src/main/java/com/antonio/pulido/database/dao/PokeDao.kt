package com.antonio.pulido.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.antonio.pulido.domain.entities.PokeEntity

@Dao
interface PokeDao {
    @Insert
    suspend fun upsert(pokemon: PokeEntity): Long

    @Query("SELECT * FROM pokemones")
    suspend fun getPokemones(): List<PokeEntity>

    @Query("DELETE FROM pokemones WHERE id = :pokemonId")
    suspend fun deletePokemonById(pokemonId: Int)

    @Query("SELECT * FROM pokemones WHERE id = :pokemonId")
    suspend fun getPokemonById(pokemonId: Int): PokeEntity?
}