package com.antonio.pulido.web

object ApiConstants {
    val serverPath = "https://pokeapi.co/api/v2/"
    const val wsPokemon = "pokemon"

    fun getPokeImage(id: Int): String{
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }
}