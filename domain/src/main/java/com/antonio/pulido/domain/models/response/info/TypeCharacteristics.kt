package com.antonio.pulido.domain.models.response.info

import com.google.gson.annotations.SerializedName

data class TypeCharacteristics(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
