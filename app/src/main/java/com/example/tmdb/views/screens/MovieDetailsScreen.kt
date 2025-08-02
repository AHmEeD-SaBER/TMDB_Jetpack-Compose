package com.example.tmdb.views.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.tmdb.R
import com.example.tmdb.contracts.MovieDetailsContract
import com.example.tmdb.views.composables.DetailsImage
import com.example.tmdb.views.composables.GenreSection
import com.example.tmdb.views.composables.MediaDescription
import com.example.tmdb.views.composables.MediaDetailsInfo
import com.example.tmdb.views.composables.MediaTitle
import com.example.tmdb.views.composables.RateLengthRow


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieId: Int?,
    state: MovieDetailsContract.MovieDetailsState = MovieDetailsContract.MovieDetailsState(),
    onEvent: (MovieDetailsContract.MovieDetailsEvent) -> Unit = {},
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope

) {

    val movie = state.movie


    LaunchedEffect(key1 = movieId) {
        movieId?.let {
            onEvent(MovieDetailsContract.MovieDetailsEvent.LoadMovieDetails(it))
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_15)),
    ) {
        item {
            sharedTransitionScope.DetailsImage(
                modifier = Modifier.fillMaxWidth(),
                posterPath = movie?.poster_path ?: "",
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
        item {

            sharedTransitionScope.MediaTitle(
                modifier = Modifier
                    .fillMaxWidth(),
                title = movie?.title ?: "",
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
        item {
            RateLengthRow(
                rating = movie?.vote_average ?: 0.0,
                voteCount = movie?.vote_count ?: 0,
                runtime = movie?.runtime ?: 0
            )
        }
        item {
            GenreSection(
                items = movie?.genres ?: emptyList()
            )

        }
        item {
            MediaDescription(
                modifier = Modifier
                    .fillMaxWidth(),
                desc = movie?.overview ?: "No description available"
            )
        }
        item {
            MediaDetailsInfo(
                languages = movie?.spoken_languages?.map { it.english_name } ?: emptyList(),
                companies = movie?.production_companies ?: emptyList()
            )

        }
    }


}
