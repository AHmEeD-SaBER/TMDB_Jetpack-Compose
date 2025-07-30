package com.example.tmdb.views.screens

import DrawerBody
import DrawerHeader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.R
import com.example.tmdb.contracts.MainContract
import com.example.tmdb.navigation.Routes
import com.example.tmdb.viewmodels.moviedetails.MovieDetailsViewModel
import com.example.tmdb.viewmodels.movies.MoviesViewModel
import com.example.tmdb.viewmodels.tv.TvViewModel
import com.example.tmdb.views.composables.CustomAppBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainContract.MainState = MainContract.MainState(),
    onEvent: (MainContract.MainEvent) -> Unit = {},
) {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(
        initialValue = if (state.isDrawerOpen) DrawerValue.Open else DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight(),
                drawerContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.95f),
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    DrawerHeader(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_25)))
                    DrawerBody(
                        items = state.drawerItems,
                        onItemClick = { item ->
                            scope.launch {
                                drawerState.close()
                            }
                            onEvent(MainContract.MainEvent.DrawerItemClicked(item.id))
                        }
                    )
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .statusBarsPadding(),
            topBar = {
                CustomAppBar(  //stringResource(),
                    title = when (state.currentRoute) {
                        Routes.HOME -> "Movies"
                        Routes.Tv -> "Tv Shows"
                        Routes.SETTINGS -> "Settings"
                        else -> "TMDb"
                    },
                    onEvent = { event ->
                        onEvent(event)
                        if (event is MainContract.MainEvent.OpenDrawer) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                )
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = state.currentRoute,
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = dimensionResource(R.dimen.padding_15))
            ) {
                composable(Routes.HOME) {
                    val viewModel: MoviesViewModel = koinViewModel()
                    MoviesScreen(
                        navController = navController,
                        state = viewModel.state.collectAsStateWithLifecycle().value,
                        effect = viewModel.effect,
                        onEvent = { event -> viewModel.onEvent(event) })
                }
                composable(Routes.Tv) {
                    val viewModel: TvViewModel = koinViewModel()
                    TvScreen(
                        state = viewModel.state.collectAsStateWithLifecycle().value,
                        onEvent = { event -> viewModel.onEvent(event) }
                    )
                }
                composable(Routes.SETTINGS) { SettingsScreen() }
                composable("${Routes.MOVIE_DETAIL}/{movieId}") { backStackEntry ->
                    val viewModel: MovieDetailsViewModel = koinViewModel()
                    val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                    MovieDetailsScreen(
                        movieId = movieId,
                        state = viewModel.state.collectAsStateWithLifecycle().value,
                        onEvent = { event -> viewModel.onEvent(event) }
                    )
                }
                composable("${Routes.TV_SHOW_DETAIL}/{tvId}") { backStackEntry ->
                    val tvId = backStackEntry.arguments?.getString("tvId")?.toIntOrNull()
                    TvDetailsScreen(tvId = tvId)
                }
            }
        }
    }
}


@Composable
fun TvDetailsScreen(tvId: Int?) {


}


@Composable
fun SettingsScreen() {
    Text(
        "Settings Screen",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}
