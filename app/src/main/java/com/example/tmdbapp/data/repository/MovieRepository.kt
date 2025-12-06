package com.example.tmdbapp.data.repository

import com.example.tmdbapp.data.model.*
import com.example.tmdbapp.data.remote.RetrofitInstance

class MovieRepository {

    private val api = RetrofitInstance.api

    // 1. GET GENRES
    suspend fun getGenres(): GenresResponse {
        return api.getGenres()
    }

    // 2. GET MOVIES BY GENRE
    suspend fun getMoviesByGenre(genreId: Int): MoviesResponse {
        return api.getMoviesByGenre(genreId)
    }

    // 3. GET MOVIE DETAIL
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return api.getMovieDetail(movieId)
    }

    // 4. GET REVIEWS
    suspend fun getReviews(movieId: Int, page: Int = 1): ReviewsResponse {
        return api.getReviews(movieId, page)
    }

    // 5. GET VIDEOS (NEW)
    suspend fun getVideos(movieId: Int): VideosResponse {
        return api.getVideos(movieId)
    }
}
