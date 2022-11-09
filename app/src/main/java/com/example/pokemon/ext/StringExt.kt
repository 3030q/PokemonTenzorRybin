package com.example.pokemon.ext

import java.util.*

fun String.capitalizeFirstCharacter(): String {
    return substring(0, 1).uppercase(Locale.getDefault()) + substring(1)
}