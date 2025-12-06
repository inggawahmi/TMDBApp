package com.example.tmdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.tmdbapp.ui.genres.GenresScreen
import com.example.tmdbapp.ui.movies.MoviesScreen
import com.example.tmdbapp.ui.detail.MovieDetailScreen   // pastikan ada import ini
import com.example.tmdbapp.ui.theme.TMDBAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TMDBAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "genres",
            modifier = Modifier.padding(paddingValues)
        ) {

            composable("genres") {
                GenresScreen(
                    onGenreClick = { genreId ->
                        navController.navigate("movies/$genreId")
                    }
                )
            }

            composable(
                route = "movies/{genreId}",
                arguments = listOf(
                    navArgument("genreId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val genreId = backStackEntry.arguments?.getInt("genreId") ?: 0

                MoviesScreen(
                    genreId = genreId,
                    onMovieClick = { movieId ->
                        navController.navigate("detail/$movieId")
                    }
                )
            }

            composable(
                route = "detail/{movieId}",
                arguments = listOf(
                    navArgument("movieId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0

                MovieDetailScreen(
                    movieId = movieId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
