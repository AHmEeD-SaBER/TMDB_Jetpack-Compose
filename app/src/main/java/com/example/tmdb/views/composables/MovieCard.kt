package com.example.tmdb.views.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tmdb.data.asMediaItem
import com.example.tmdb.data.movies.Movie
import com.example.tmdb.contracts.MovieContract

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onEvent: (MovieContract.MoviesEvent) -> Unit = {},
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
    MediaItemCard(
        modifier = modifier,
        mediaItem = movie.asMediaItem,
        onItemClicked = { id -> onEvent(MovieContract.MoviesEvent.MovieClicked(id)) },
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = sharedTransitionScope
    )
}

