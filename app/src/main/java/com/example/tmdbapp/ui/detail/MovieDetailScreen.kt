package com.example.tmdbapp.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tmdbapp.data.model.MovieDetailResponse
import com.example.tmdbapp.data.model.Video

private const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = viewModel(),
    onBack: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.load(movieId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = state.movie?.title ?: "Movie Detail")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        val movie = state.movie  // <<< FIX: local variable (smart-cast friendly)

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.error != null -> {
                    Text(
                        text = "Error: ${state.error}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                movie != null -> {
                    MovieDetailContent(
                        movie = movie,
                        videos = state.videos,
                        onPlayVideo = { videoKey ->
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.youtube.com/watch?v=$videoKey")
                            )
                            ctx.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDetailContent(
    movie: MovieDetailResponse,
    videos: List<Video>,
    onPlayVideo: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        movie.poster_path?.let { poster ->
            AsyncImage(
                model = IMAGE_BASE + poster,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(
            movie.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("⭐ ${movie.vote_average}")
            movie.runtime?.let { Text("${it} min") }
            movie.release_date?.let { Text(it) }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            "Overview",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(6.dp))
        Text(movie.overview ?: "No overview")

        Spacer(Modifier.height(20.dp))

        if (videos.isNotEmpty()) {
            Text(
                "Trailer",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(10.dp))

            videos
                .filter { it.site.equals("YouTube", true) }
                .forEach { v ->
                    Button(
                        onClick = { onPlayVideo(v.key) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = v.name)
                    }
                    Spacer(Modifier.height(6.dp))
                }
        }
    }
}
