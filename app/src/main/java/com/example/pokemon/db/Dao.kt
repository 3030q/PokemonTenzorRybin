package com.example.pokemon.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): LiveData<List<PokemonLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemonLocal: PokemonLocal)
}