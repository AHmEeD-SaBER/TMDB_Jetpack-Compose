package com.example.tmdb.viewmodels.movies.usecases

import com.example.tmdb.data.TMDbError
import com.example.tmdb.data.movies.Movie
import com.example.tmdb.network.INetworkMonitor
import com.example.tmdb.network.IRetrofitService
import com.example.tmdb.utils.Constants
import javax.inject.Inject

class GetMoviesUseCase(
    private val api: IRetrofitService,
    private val networkMonitor: INetworkMonitor
) : IGetMoviesUseCase {

    override suspend fun getMovies(): Result<List<Movie>> {
        val isNetworkAvailable = networkMonitor.observeNetwork()
        if (!isNetworkAvailable) {
            return Result.failure(TMDbError.NetworkError(Constants.ErrorMessages.NETWORK_ERROR))
        }
        return try {
            val response = api.getPopularMovies()
            if (response.isSuccessful) {
                Result.success(response.body()?.results ?: emptyList())
            } else {
                Result.failure(
                    TMDbError.DataError(Constants.ErrorMessages.DATA_ERROR)

                )
            }
        } catch (e: TMDbError) {
            Result.failure(e)
        }
    }

}