package com.example.tmdbapp.ui.detail

import com.example.tmdbapp.data.model.MovieDetailResponse
import com.example.tmdbapp.data.model.Video

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetailResponse? = null,
    val videos: List<Video> = emptyList(),
    val error: String? = null
)
