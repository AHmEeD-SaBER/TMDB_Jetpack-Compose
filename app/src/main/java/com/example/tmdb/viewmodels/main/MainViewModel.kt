package com.example.tmdb.viewmodels.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tmdb.contracts.MainContract
import com.example.tmdb.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(MainContract.MainState())
    val state: StateFlow<MainContract.MainState> = _state.asStateFlow()

    fun onEvent(event: MainContract.MainEvent) {
        when (event) {
            is MainContract.MainEvent.OpenDrawer -> {
                _state.update { it.copy(isDrawerOpen = true) }
                Log.d("MainViewModel", "Drawer opened")
            }

            is MainContract.MainEvent.CloseDrawer -> {
                _state.update { it.copy(isDrawerOpen = false) }
            }

            is MainContract.MainEvent.DrawerItemClicked -> {
                val selectedRoute = when (event.itemId) {
                    1 -> Routes.HOME
                    2 -> Routes.Tv
                    3 -> Routes.SETTINGS
                    else -> Routes.HOME
                }
                _state.update {
                    it.copy(
                        isDrawerOpen = false,
                        currentRoute = selectedRoute
                    )
                }
            }
        }
    }
}

