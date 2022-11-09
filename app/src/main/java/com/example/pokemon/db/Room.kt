package com.example.pokemon.db

import android.content.Context
import androidx.room.*

@Database(entities = [PokemonLocal::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class PokemonDatabase: RoomDatabase() {
    abstract val dao: Dao
}

private lateinit var INSTANCE: PokemonDatabase

fun getDatabase(context: Context): PokemonDatabase {
    synchronized(PokemonDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, PokemonDatabase::class.java, "pokemon").build()
        }
    }
    return INSTANCE
}