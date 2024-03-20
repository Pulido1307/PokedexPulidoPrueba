package com.antonio.pulido.domain.mappers

import com.antonio.pulido.domain.entities.PokeEntity
import com.antonio.pulido.domain.models.response.info.PokeInfoResponse

object PokeMapper {
    fun toEntity(pokeInfoResponse: PokeInfoResponse) = PokeEntity(
        id = pokeInfoResponse.id,
        name = pokeInfoResponse.name,
        types = pokeInfoResponse.types.map { it.type.name }.toString().replace("[", "").replace("]", ""),
        height = pokeInfoResponse.height,
        weight = pokeInfoResponse.weight,
        urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokeInfoResponse.id}.png"
    )


}