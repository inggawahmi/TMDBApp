package com.example.tmdbapp.data.remote

import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    /**
     * 1. GET GENRES
     * Endpoint: /genre/movie/list
     * Untuk mengambil semua genre film (Action, Drama, Horror, dll)
     */
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): GenresResponse


    /**
     * 2. GET MOVIES BY GENRE
     * Endpoint: /discover/movie?with_genres={id}
     * Untuk mengambil list film berdasarkan genre tertentu
     */
    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse


    /**
     * 3. DISCOVER MOVIES (dengan paging)
     * Endpoint: /discover/movie?with_genres={id}&page={page}
     * Untuk pagination
     */
    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse


    /**
     * 4. GET MOVIE DETAIL
     * Endpoint: /movie/{id}
     * Untuk halaman detail film
     */
    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieDetailResponse


    /**
     * 5. GET REVIEWS
     * Endpoint: /movie/{id}/reviews
     * Untuk menampilkan review user (di halaman detail)
     */
    @GET("movie/{id}/reviews")
    suspend fun getReviews(
        @Path("id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): ReviewsResponse


    /**
     * 6. GET VIDEOS (TRAILERS)
     * Endpoint: /movie/{id}/videos
     * Untuk ambil list video seperti trailer, teaser, behind-the-scenes.
     */
    @GET("movie/{id}/videos")
    suspend fun getVideos(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): VideosResponse
}
