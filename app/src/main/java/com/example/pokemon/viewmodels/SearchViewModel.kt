package com.example.pokemon.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokemon.db.getDatabase
import com.example.pokemon.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application): AndroidViewModel(application) {

    private val repository = Repository(getDatabase(application))

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorText: MutableLiveData<String> = MutableLiveData()
    val errorText: LiveData<String> = _errorText

    private val _validationError: MutableLiveData<String> = MutableLiveData()
    val validationError: LiveData<String> = _validationError

    private val _result: MutableLiveData<Boolean> = MutableLiveData()
    val result: LiveData<Boolean> = _result


    fun findPokemon(name: String) {
        if (name.isEmpty()) {
            _validationError.value = "Введите название покемона"
            return
        }
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getPokemon(name)
                _result.postValue(result)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _result.postValue(false)
                _isLoading.postValue(false)
                _errorText.postValue(e.localizedMessage)
            }
        }
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}