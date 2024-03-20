package com.antonio.pulido.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemones")
data class PokeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val types: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val urlImage: String = ""
)
