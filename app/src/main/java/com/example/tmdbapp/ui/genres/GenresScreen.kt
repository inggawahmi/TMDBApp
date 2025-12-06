package com.example.tmdbapp.ui.genres

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GenresScreen(
    viewModel: GenresViewModel = viewModel(),
    onGenreClick: (Int) -> Unit = {}
) {
    val genres by viewModel.genres.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(genres) { genre ->
            Text(
                text = genre.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onGenreClick(genre.id) }
                    .padding(16.dp)
            )
        }
    }
}
