package com.example.tmdb.data

interface MediaItem {
    val id: Int
    val poster_path: String
    val title: String
    val releaseDate: String
    val vote_average: Double
    val vote_count: Int
}
