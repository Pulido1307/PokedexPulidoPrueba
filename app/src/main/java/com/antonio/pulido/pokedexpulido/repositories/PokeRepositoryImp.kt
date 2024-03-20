package com.antonio.pulido.pokedexpulido.repositories

import com.antonio.pulido.domain.models.response.PokeResponse
import com.antonio.pulido.web.ApiService
import com.antonio.pulido.web.BaseApiResponse
import com.antonio.pulido.web.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PokeRepositoryImp  @Inject constructor(
    private val apiService: ApiService
) : PokeRepository, BaseApiResponse(){
    override suspend fun getPokeList(
        limit: Int,
        offset: Int
    ): Flow<NetworkResult<PokeResponse>> = safeApiCall{
        apiService.getPokeList(limit, offset)
    }
}