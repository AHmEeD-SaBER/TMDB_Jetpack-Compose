package com.example.tmdb.viewmodels.moviedetails.usecases

import com.example.tmdb.network.IRetrofitService
import javax.inject.Inject

class GetMovieUseCase(private val api: IRetrofitService) : IGetMovieUseCase {


    override suspend fun getMovieDetails(movieId: Int) =
        runCatching {
            api.getMovieDetails(movieId).let { response ->
                if (response.isSuccessful) {
                    response.body() ?: throw Exception("No movie details found")
                } else {
                    throw Exception("Error fetching movie details: ${response.errorBody()?.string()}")
                }
            }
        }

}