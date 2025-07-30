package com.example.tmdb.views.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tmdb.data.asMediaItem
import com.example.tmdb.data.movies.Movie
import com.example.tmdb.contracts.MovieContract

@Composable
fun MovieCard(modifier: Modifier = Modifier, movie: Movie, onEvent: (MovieContract.MoviesEvent) -> Unit = {}) {
    MediaItemCard(
        modifier = modifier,
        mediaItem = movie.asMediaItem,
        onItemClicked = { id -> onEvent(MovieContract.MoviesEvent.MovieClicked(id)) }
    )
}

