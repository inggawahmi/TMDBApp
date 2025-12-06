package com.example.tmdbapp.data.model

data class MovieDetailResponse(
    val id: Int,
    val title: String,
    val overview: String?,
    val release_date: String?,
    val runtime: Int?,
    val vote_average: Double,
    val genres: List<Genre>,
    val backdrop_path: String?,
    val poster_path: String?
)