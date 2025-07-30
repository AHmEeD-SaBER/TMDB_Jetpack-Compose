package com.example.tmdb.utils

object Constants {
    object EndPoints{
        const val MOVIES = "movie/popular"
        const val TV_SHOW = "trending/tv/week"
        const val MOVIE_DETAILS = "movie/{movie_id}"
    }
    object QueryParams {
        const val API_KEY = "api_key"
        const val MOVIE_ID = "movie_id"

    }
    object ErrorMessages {
        const val NETWORK_ERROR = "Please check your internet connection and try again."
        const val DATA_ERROR = "No data found. Please try again later."
        const val UNKNOWN_ERROR = "An unknown error occurred. Please try again later."
    }
    object ErrorTitles {
        const val NETWORK_ERROR = "Network Error"
        const val DATA_ERROR = "No Data Found"
        const val UNKNOWN_ERROR = "Unknown Error"
    }
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "e2669d4bb6934afe70f92afab29a8d4b"
}
