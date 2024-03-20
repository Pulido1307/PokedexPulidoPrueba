package com.antonio.pulido.pokedexpulido.repositories.pokemon.remote

import com.antonio.pulido.domain.models.response.info.PokeInfoResponse
import com.antonio.pulido.domain.models.response.list.PokeResponse
import com.antonio.pulido.web.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    suspend fun getPokeList(limit: Int,offset: Int): Flow<NetworkResult<PokeResponse>>
    suspend fun getPokemonInfo(id: Int): Flow<NetworkResult<PokeInfoResponse>>
}