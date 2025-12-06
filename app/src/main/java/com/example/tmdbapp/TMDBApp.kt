package com.example.tmdbapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.tmdbapp.ui.genres.GenresScreen

@Composable
fun TMDBApp() {
    MaterialTheme {
        Surface {
            GenresScreen()
        }
    }
}
