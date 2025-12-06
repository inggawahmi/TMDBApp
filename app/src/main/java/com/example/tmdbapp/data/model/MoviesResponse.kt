package com.example.tmdbapp.data.model

data class MovieItem(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val release_date: String?
)

data class MoviesResponse(
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int
)