package com.example.tmdb.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    abstract val title: String

    @Serializable
    data class MovieDetail(val movieId: Int) : Routes() {
        override val title: String = "Movie Detail"
    }

    @Serializable
    data class Movies(override val title: String = "Movies") : Routes()

    @Serializable
    data class TvDetail(val tvId: Int) : Routes() {
        override val title: String = "Tv Detail"
    }

    @Serializable
    data class Tvs(override val title: String = "Tv Shows") : Routes()

    @Serializable
    data class Settings(override val title: String = "Settings") : Routes()
}
