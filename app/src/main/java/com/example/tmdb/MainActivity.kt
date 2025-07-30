package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tmdb.ui.theme.TMDbTheme
import com.example.tmdb.viewmodels.main.MainViewModel
import com.example.tmdb.views.screens.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TMDbTheme {
                val viewModel: MainViewModel = koinViewModel()
                MainScreen(
                    state = viewModel.state.collectAsStateWithLifecycle().value,
                    onEvent = viewModel::onEvent,
                )
            }
        }
    }
}
