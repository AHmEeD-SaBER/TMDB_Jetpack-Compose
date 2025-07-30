package com.example.tmdb.viewmodels.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.contracts.MovieContract
import com.example.tmdb.data.TMDbError
import com.example.tmdb.utils.Constants
import com.example.tmdb.viewmodels.movies.usecases.IGetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: IGetMoviesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieContract.MoviesState())
    val state: StateFlow<MovieContract.MoviesState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MovieContract.MovieEffect>()
    val effect = _effect.asSharedFlow()


    init {
        onEvent(MovieContract.MoviesEvent.LoadMovies)
    }

    fun onEvent(event: MovieContract.MoviesEvent) {
        when (event) {
            is MovieContract.MoviesEvent.LoadMovies -> loadMovies()
            is MovieContract.MoviesEvent.MovieClicked -> onMovieClicked(event.movieId)

            MovieContract.MoviesEvent.Retry -> onRetry()
        }
    }

    private fun onMovieClicked(movieId: Int) {
        viewModelScope.launch {
            _effect.emit(MovieContract.MovieEffect.NavigateToMovieDetails(movieId))
        }

    }

    private fun onRetry() {
        onEvent(MovieContract.MoviesEvent.LoadMovies)
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            getMoviesUseCase.getMovies()
                .onSuccess { movies ->
                    if (movies.isEmpty()) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = TMDbError.DataError(Constants.ErrorMessages.DATA_ERROR)
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                movies = movies
                            )
                        }
                    }
                }
                .onFailure { exception ->
                    val error = exception as? TMDbError ?: TMDbError.UnknownError(
                        exception.message ?: "Unknown error"
                    )
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error
                        )
                    }
                }

        }
    }
}