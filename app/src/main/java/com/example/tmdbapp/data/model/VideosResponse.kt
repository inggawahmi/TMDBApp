package com.example.tmdbapp.data.model

data class Video(
    val id: String,
    val key: String,
    val site: String,
    val name: String,
    val type: String
)

data class VideosResponse(
    val id: Int,
    val results: List<Video>
)
