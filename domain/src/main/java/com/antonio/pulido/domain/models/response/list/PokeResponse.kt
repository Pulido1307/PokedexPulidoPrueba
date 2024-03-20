package com.antonio.pulido.domain.models.response.list

import com.google.gson.annotations.SerializedName

data class PokeResponse(
    @SerializedName("results") val results: List<PokeListResult> = listOf()
)
