package com.example.tmdbapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.model.MovieDetailResponse
import com.example.tmdbapp.data.model.Video
import com.example.tmdbapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun load(movieId: Int) {

        viewModelScope.launch {

            // Set loading state
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                // Fetch detail film
                val movieDetail = RetrofitInstance.api.getMovieDetail(movieId)

                // Fetch videos
                val videosResponse = RetrofitInstance.api.getVideos(movieId)

                // Update final state
                _uiState.value = MovieDetailUiState(
                    isLoading = false,
                    movie = movieDetail,
                    videos = videosResponse.results
                )

            } catch (e: Exception) {

                _uiState.value = MovieDetailUiState(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}
