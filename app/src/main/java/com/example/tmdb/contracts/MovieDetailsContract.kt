package com.example.tmdb.contracts

import com.example.tmdb.contracts.TvContract.TvsEvent
import com.example.tmdb.data.TMDbError
import com.example.tmdb.data.moviedetails.MovieDetailsResponse

class MovieDetailsContract {
    data class MovieDetailsState(
        val movie: MovieDetailsResponse? = null,
        val isLoading: Boolean = false,
        val error: TMDbError? = null
    )

    sealed class MovieDetailsEvent {
        data class LoadMovieDetails(val movieId: Int) : MovieDetailsEvent()
        data object Back : MovieDetailsEvent()
        data object Retry : MovieDetailsEvent()
        data object CheckNetwork : MovieDetailsEvent()
    }

}