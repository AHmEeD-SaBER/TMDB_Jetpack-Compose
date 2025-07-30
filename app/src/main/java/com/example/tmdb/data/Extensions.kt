package com.example.tmdb.data

import com.example.tmdb.data.movies.Movie
import com.example.tmdb.data.tv.Tv

val Movie.asMediaItem: MediaItem
    get() = object : MediaItem {
        override val id: Int = this@asMediaItem.id
        override val poster_path: String = this@asMediaItem.poster_path
        override val title: String = this@asMediaItem.title
        override val releaseDate: String = this@asMediaItem.release_date
        override val vote_average: Double = this@asMediaItem.vote_average
        override val vote_count: Int = this@asMediaItem.vote_count
    }

val Tv.asMediaItem: MediaItem
    get() = object : MediaItem {
        override val id: Int = this@asMediaItem.id
        override val poster_path: String = this@asMediaItem.poster_path
        override val title: String = this@asMediaItem.name
        override val releaseDate: String = this@asMediaItem.first_air_date
        override val vote_average: Double = this@asMediaItem.vote_average
        override val vote_count: Int = this@asMediaItem.vote_count
    }


val String.getFullPosterPath: String
    get() = "https://image.tmdb.org/t/p/w500$this"