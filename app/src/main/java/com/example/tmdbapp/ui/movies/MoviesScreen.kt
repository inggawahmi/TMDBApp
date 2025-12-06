package com.example.tmdbapp.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tmdbapp.data.model.MovieItem


@Composable
fun MoviesScreen(
    genreId: Int,
    viewModel: MoviesViewModel = viewModel(),
    onMovieClick: (Int) -> Unit = {}
) {
    LaunchedEffect(genreId) {
        viewModel.loadMovies(genreId)
    }

    val movies by viewModel.movies.collectAsState()

    LaunchedEffect(genreId) {
        viewModel.loadMovies(genreId)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie: MovieItem ->
            Text(
                text = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onMovieClick(movie.id) }
            )
        }
    }
}