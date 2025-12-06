package com.example.tmdbapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.model.MovieItem
import com.example.tmdbapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieItem>>(emptyList())
    val movies = _movies.asStateFlow()

    fun loadMovies(genreId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getMoviesByGenre(genreId)
                _movies.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}