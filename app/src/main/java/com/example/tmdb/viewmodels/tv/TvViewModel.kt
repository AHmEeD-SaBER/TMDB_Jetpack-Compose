package com.example.tmdb.viewmodels.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.contracts.MovieContract
import com.example.tmdb.contracts.MovieDetailsContract
import com.example.tmdb.contracts.TvContract
import com.example.tmdb.data.TMDbError
import com.example.tmdb.network.INetworkMonitor
import com.example.tmdb.utils.Constants
import com.example.tmdb.viewmodels.tv.usecases.IGetTvsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TvViewModel @Inject constructor(
    private val getTvsUseCase: IGetTvsUseCase,
    private val networkMonitor: INetworkMonitor
) : ViewModel() {
    private val _state = MutableStateFlow(TvContract.TvsState())
    val state: StateFlow<TvContract.TvsState> = _state.asStateFlow()

    init {
        onEvent(TvContract.TvsEvent.CheckNetwork)
    }

    fun onEvent(event: TvContract.TvsEvent) {
        when (event) {
            is TvContract.TvsEvent.LoadTvs -> loadTvs()
            is TvContract.TvsEvent.TvClicked -> {
            }

            is TvContract.TvsEvent.CheckNetwork -> checkNetwork()
            is TvContract.TvsEvent.Retry -> onRetry()
        }
    }

    private fun onRetry() {
        _state.update { it.copy(isLoading = true, error = null) }
        onEvent(TvContract.TvsEvent.CheckNetwork)
    }

    private fun checkNetwork() {
        viewModelScope.launch {
            val isConnected = networkMonitor.observeNetwork()
            if (isConnected) {
                onEvent(TvContract.TvsEvent.LoadTvs)
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = TMDbError.NetworkError(Constants.ErrorMessages.NETWORK_ERROR)
                    )
                }
            }
        }
    }

    private fun loadTvs() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            getTvsUseCase.getTvs()
                .onSuccess { tvs ->
                    if (tvs.isEmpty()) {
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
                                tvs = tvs
                            )
                        }
                    }
                }
                .onFailure { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = TMDbError.UnknownError(
                                exception.message ?: Constants.ErrorMessages.UNKNOWN_ERROR
                            )
                        )
                    }
                }
        }
    }

}