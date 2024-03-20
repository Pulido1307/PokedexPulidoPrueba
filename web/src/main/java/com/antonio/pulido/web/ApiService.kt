package com.antonio.pulido.web

import com.antonio.pulido.domain.models.response.info.PokeInfoResponse
import com.antonio.pulido.domain.models.response.list.PokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiConstants.wsPokemon)
    suspend fun getPokeList(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<PokeResponse>
    @GET(ApiConstants.wsPokemon+ "/{id}/")
    suspend fun getPokemonInfo(@Path("id") id: Int): Response<PokeInfoResponse>
}