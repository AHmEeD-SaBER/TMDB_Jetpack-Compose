package com.example.tmdb.network

import com.example.tmdb.data.moviedetails.MovieDetailsResponse
import com.example.tmdb.data.movies.MoviesResponse
import com.example.tmdb.data.tv.TvResponse
import com.example.tmdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRetrofitService {
    @GET(Constants.EndPoints.MOVIES)
    suspend fun getPopularMovies(@Query(Constants.QueryParams.API_KEY) apiKey: String = Constants.API_KEY): Response<MoviesResponse>

    @GET(Constants.EndPoints.TV_SHOW)
    suspend fun getTrendingTVShows(@Query(Constants.QueryParams.API_KEY) apiKey: String = Constants.API_KEY): Response<TvResponse>

    @GET(Constants.EndPoints.MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path(Constants.QueryParams.MOVIE_ID) movieId: Int,
        @Query(Constants.QueryParams.API_KEY) apiKey: String = Constants.API_KEY
    ): Response<MovieDetailsResponse>
}