package com.antonio.pulido.domain.models.response

import com.google.gson.annotations.SerializedName

data class PokeResponse(
    @SerializedName("results") val results: List<PokeListResult> = listOf()
)
