package com.example.tmdb.views.screens

import DrawerBody
import DrawerHeader
import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tmdb.R
import com.example.tmdb.contracts.MainContract
import com.example.tmdb.navigation.Routes
import com.example.tmdb.navigation.defaultEnterTransition
import com.example.tmdb.navigation.defaultExitTransition
import com.example.tmdb.viewmodels.moviedetails.MovieDetailsViewModel
import com.example.tmdb.viewmodels.movies.MoviesViewModel
import com.example.tmdb.viewmodels.tv.TvViewModel
import com.example.tmdb.views.composables.CustomAppBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
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
                val currentDestination =
                    navController.currentBackStackEntryAsState().value?.destination
                val isMovieDetailsScreen =
                    currentDestination?.route?.contains("MovieDetail") == true

                CustomAppBar(
                    title = state.currentRoute.title,
                    showBackButton = isMovieDetailsScreen,
                    onEvent = { event ->
                        onEvent(event)
                        if (event is MainContract.MainEvent.OpenDrawer) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        ) { padding ->
            SharedTransitionLayout {
                NavHost(
                    navController = navController,
                    startDestination = state.currentRoute,
                    modifier = Modifier
                        .padding(padding)
                        .padding(horizontal = dimensionResource(R.dimen.padding_15))
                ) {
                    composable<Routes.Movies>(
                        enterTransition = { defaultEnterTransition() },
                        popEnterTransition = { defaultEnterTransition() },
                    ) {
                        val viewModel: MoviesViewModel = koinViewModel()
                        MoviesScreen(
                            navController = navController,
                            state = viewModel.state.collectAsStateWithLifecycle().value,
                            effect = viewModel.effect,
                            onEvent = { event -> viewModel.onEvent(event) },
                            animatedVisibilityScope = this,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                    }
                    composable<Routes.Tvs>(
                        enterTransition = { defaultEnterTransition() },
                        popEnterTransition = { defaultEnterTransition() },
                    ) {
                        val viewModel: TvViewModel = koinViewModel()
                        TvScreen(
                            state = viewModel.state.collectAsStateWithLifecycle().value,
                            onEvent = { event -> viewModel.onEvent(event) },
                            animatedVisibilityScope = this,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                    }
                    composable<Routes.Settings>(
                        enterTransition = { defaultEnterTransition() },
                        exitTransition = { defaultExitTransition() }
                    ) { SettingsScreen() }
                    composable<Routes.MovieDetail>(
                    ) { backStackEntry ->
                        val viewModel: MovieDetailsViewModel = koinViewModel()
                        val movieId = backStackEntry.toRoute<Routes.MovieDetail>().movieId
                        MovieDetailsScreen(
                            movieId = movieId,
                            state = viewModel.state.collectAsStateWithLifecycle().value,
                            onEvent = { event -> viewModel.onEvent(event) },
                            animatedVisibilityScope = this,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                    }
                    composable<Routes.TvDetail>(
                        enterTransition = { defaultEnterTransition() },
                        exitTransition = { defaultExitTransition() }
                    ) { backStackEntry ->
                        val tvId = backStackEntry.toRoute<Routes.TvDetail>().tvId
                        TvDetailsScreen(tvId = tvId)
                    }
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
