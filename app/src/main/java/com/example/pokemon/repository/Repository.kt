package com.example.pokemon.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.pokemon.db.PokemonDatabase
import com.example.pokemon.db.asDomainModel
import com.example.pokemon.model.Pokemon
import com.example.pokemon.network.Client
import com.example.pokemon.network.asDbModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class Repository(private val database: PokemonDatabase) {

    val pokemons: LiveData<List<Pokemon>> = Transformations.map(database.dao.getAll()) {
        it.asDomainModel()
    }

    private val client = Client.create()


    suspend fun getPokemon(name: String): Boolean {
        val response = client.getPokemon(name)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                database.dao.insert(body.asDbModel() ?: throw IOException("Что-то пошло не так, покемон не пойман"))
                return true
            } else throw IOException("Пустое тело")
        } else throw IOException("Сеть - ${response.code()}")
    }
}