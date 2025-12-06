package com.example.tmdbapp.ui.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.model.Genre
import com.example.tmdbapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GenresViewModel : ViewModel() {

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres = _genres.asStateFlow()

    init {
        loadGenres()
    }

    private fun loadGenres() {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.getGenres()
                println("TMDB GENRES RESULT = ${result.genres}")
                _genres.value = result.genres
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}