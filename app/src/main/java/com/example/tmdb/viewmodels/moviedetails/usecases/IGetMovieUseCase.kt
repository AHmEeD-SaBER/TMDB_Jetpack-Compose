package com.example.tmdb.viewmodels.moviedetails.usecases

import com.example.tmdb.data.moviedetails.MovieDetailsResponse

interface IGetMovieUseCase {
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsResponse>
}