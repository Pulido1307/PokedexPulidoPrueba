package com.antonio.pulido.pokedexpulido.util

fun String.isNumeric(): Boolean {
    return try {
        this.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}