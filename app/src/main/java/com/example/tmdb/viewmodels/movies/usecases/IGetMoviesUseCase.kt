package com.example.tmdb.viewmodels.movies.usecases

import com.example.tmdb.data.movies.Movie

interface IGetMoviesUseCase {
    suspend fun getMovies(): Result<List<Movie>>
}