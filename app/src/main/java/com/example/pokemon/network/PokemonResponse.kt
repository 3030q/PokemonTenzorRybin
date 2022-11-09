package com.example.pokemon.network

import com.example.pokemon.db.PokemonLocal
import com.squareup.moshi.Json

class PokemonResponse {
    val id: Int? = null
    val name: String? = null
    val sprites: Sprites? = null
    @field:Json(name = "types")
    val type: List<Type>? = null

    class Type {

        val type: Type? = null

        class Type {

            val name: String? = null
        }
    }

    class Sprites {
        val other: Other? = null

        class Other {
            @field:Json(name = "official-artwork")
            val artwork: OfficialArtwork? = null

            class OfficialArtwork {
                @field:Json(name = "front_default")
                val url: String? = null;
            }
        }
    }
}

fun PokemonResponse.asDbModel(): PokemonLocal? {

    return PokemonLocal(
        id = id ?: return null,
        name = name ?: "",
        imageUrl = sprites?.other?.artwork?.url ?: "",
        types = type?.map { it.type?.name ?: "" }
    )
}