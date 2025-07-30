package com.example.tmdb.contracts

import com.example.tmdb.data.TMDbError
import com.example.tmdb.data.movies.Movie

class MovieContract {
    data class MoviesState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val error: TMDbError? = null,
    )

    sealed class MoviesEvent {
        data object LoadMovies : MoviesEvent()
        data class MovieClicked(val movieId: Int) : MoviesEvent()
        data object Retry : MoviesEvent()
    }

    sealed class MovieEffect {
        data class NavigateToMovieDetails(val movieId: Int) : MovieEffect()
    }
}
