package com.antonio.pulido.pokedexpulido.repositories.remote

import com.antonio.pulido.domain.models.response.info.PokeInfoResponse
import com.antonio.pulido.domain.models.response.list.PokeResponse
import com.antonio.pulido.web.ApiService
import com.antonio.pulido.web.BaseApiResponse
import com.antonio.pulido.web.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : PokeRepository, BaseApiResponse() {
    override suspend fun getPokeList(
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<PokeResponse>> = safeApiCall {
        apiService.getPokeList(limit, offset)
    }

    override suspend fun getPokemonInfo(id: Int): Flow<NetworkResult<PokeInfoResponse>> =
        safeApiCall {
            apiService.getPokemonInfo(id)
        }
}