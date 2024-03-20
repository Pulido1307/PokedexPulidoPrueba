package com.antonio.pulido.pokedexpulido.repositories

import com.antonio.pulido.domain.models.response.PokeResponse
import com.antonio.pulido.web.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PokeRepository {
    suspend fun getPokeList(limit: Int,offset: Int): Flow<NetworkResult<PokeResponse>>
}