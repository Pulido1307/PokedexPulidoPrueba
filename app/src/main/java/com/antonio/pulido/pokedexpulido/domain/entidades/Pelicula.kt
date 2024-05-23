package com.antonio.pulido.pokedexpulido.domain.entidades

data class Pelicula(
    val codigo: String? = "",
    val nombre: String? = "",
    val idioma: String? = "",
    val descripcion: String? = "",
    val duracion: Int? = 0,
    val year: Int? = 0,
    val genero: String? = ""
)
