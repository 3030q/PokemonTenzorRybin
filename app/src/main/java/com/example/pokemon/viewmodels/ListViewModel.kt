package com.example.pokemon.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.pokemon.db.getDatabase
import com.example.pokemon.model.Pokemon
import com.example.pokemon.repository.Repository
import kotlinx.coroutines.launch

class ListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = Repository(getDatabase(application))

    val pokemons = repository.pokemons


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}