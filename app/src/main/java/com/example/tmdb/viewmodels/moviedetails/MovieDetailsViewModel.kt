package com.example.tmdb.viewmodels.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.contracts.MovieDetailsContract
import com.example.tmdb.data.TMDbError
import com.example.tmdb.viewmodels.moviedetails.usecases.IGetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieDetailsViewModel @Inject constructor(private val getMovieUseCase: IGetMovieUseCase,) :
    ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsContract.MovieDetailsState())
    var state = _state.asStateFlow()


    fun onEvent(event: MovieDetailsContract.MovieDetailsEvent) {
        when (event) {
            is MovieDetailsContract.MovieDetailsEvent.LoadMovieDetails -> {
                loadMovieDetails(event.movieId)
            }

            MovieDetailsContract.MovieDetailsEvent.Back -> {
            }

            MovieDetailsContract.MovieDetailsEvent.CheckNetwork -> TODO()
            MovieDetailsContract.MovieDetailsEvent.Retry -> TODO()
        }
    }

    private fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            getMovieUseCase.getMovieDetails(movieId)
                .onSuccess { movieDetails ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        movie = movieDetails
                    )
                }
                .onFailure { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = TMDbError.UnknownError(exception.message ?: "Unknown error")
                    )
                }
        }
    }


}