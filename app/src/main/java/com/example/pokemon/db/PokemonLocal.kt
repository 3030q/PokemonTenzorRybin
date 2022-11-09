package com.example.pokemon.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemon.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonLocal constructor(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>?
)


fun List<PokemonLocal>.asDomainModel(): List<Pokemon> {
    return map {
        Pokemon(
            id = it.id,
            name = it.name,
            url = it.imageUrl,
            types = it.types
        )
    }
}