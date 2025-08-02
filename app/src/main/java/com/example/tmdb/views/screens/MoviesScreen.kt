package com.example.tmdb.views.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.tmdb.R
import com.example.tmdb.contracts.MovieContract
import com.example.tmdb.navigation.Routes
import com.example.tmdb.views.composables.ErrorSection
import com.example.tmdb.views.composables.MovieCard
import kotlinx.coroutines.flow.SharedFlow


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    state: MovieContract.MoviesState = MovieContract.MoviesState(),
    effect: SharedFlow<MovieContract.MovieEffect>,
    onEvent: (MovieContract.MoviesEvent) -> Unit = {},
    navController: androidx.navigation.NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {

    LaunchedEffect(Unit) {
        effect.collect { e ->
            when (e) {
                is MovieContract.MovieEffect.NavigateToMovieDetails -> {
                    navController.navigate(
                        Routes.MovieDetail(e.movieId)
                    )
                }
            }
        }
    }

    Column(
        modifier
            .fillMaxSize()
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            ErrorSection(
                modifier = Modifier.fillMaxSize(),
                error = state.error,
                onEvent = { onEvent(MovieContract.MoviesEvent.Retry) }
            )

        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.item_spacing))
            ) {
                items(state.movies) { movie ->
                    MovieCard(
                        movie = movie,
                        modifier = modifier,
                        onEvent = { event -> onEvent(event) },
                        animatedVisibilityScope = animatedVisibilityScope,
                        sharedTransitionScope = sharedTransitionScope
                    )
                }
            }
        }
    }
}

