package com.example.pokemon.db

import androidx.room.TypeConverter

class ListConverter {

    val separator = "**;;**"

    @TypeConverter
    fun fromList(value: List<String>?): String? {
        if (value == null) return null
        var result = ""
        value.forEach {
            result += "$it$separator"
        }
        return result.dropLast(separator.length)
    }

    @TypeConverter
    fun toList(value: String?): List<String>? {
        if (value == null) return null
        return value.split(separator)
    }
}