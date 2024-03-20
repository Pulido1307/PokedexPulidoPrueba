package com.antonio.pulido.domain.models.response.info

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokeInfoResponse(
     @SerializedName("id") val id: Int = 0,
     @SerializedName("name") val name: String = "",
     @SerializedName("weight") val weight: Int = 0,
     @SerializedName("height") val height: Int = 0,
     @SerializedName("types") val types: List<Type> = listOf()
)
