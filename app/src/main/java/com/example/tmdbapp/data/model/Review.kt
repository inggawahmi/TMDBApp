package com.example.tmdbapp.data.model

data class Review(
    val id: String,
    val author: String,
    val content: String
)

data class ReviewsResponse(
    val page: Int,
    val results: List<Review>,
    val total_pages: Int
)