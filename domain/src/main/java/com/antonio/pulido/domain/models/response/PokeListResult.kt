package com.antonio.pulido.domain.models.response

import com.google.gson.annotations.SerializedName

data class PokeListResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
