package com.example.tmdb.contracts

import com.example.tmdb.data.TMDbError
import com.example.tmdb.data.movies.Movie
import com.example.tmdb.data.tv.Tv

class TvContract {
    data class TvsState(
        val isLoading: Boolean = false,
        val tvs: List<Tv> = emptyList(),
        val error: TMDbError? = null,
    )

    sealed class TvsEvent{
        data object LoadTvs : TvsEvent()
        data class TvClicked(val tvId: Int) : TvsEvent()
        data object Retry : TvsEvent()
        data object CheckNetwork : TvsEvent()
    }
}