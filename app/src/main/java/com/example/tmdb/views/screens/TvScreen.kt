package com.example.tmdb.views.screens

import android.util.Log
import com.example.tmdb.viewmodels.tv.TvViewModel
import com.example.tmdb.views.composables.TvCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tmdb.R
import com.example.tmdb.contracts.TvContract
import com.example.tmdb.views.composables.ErrorSection


@Composable
fun TvScreen(
    modifier: Modifier = Modifier,
    state: TvContract.TvsState = TvContract.TvsState(),
    onEvent: (TvContract.TvsEvent) -> Unit = {},

    ) {

    Column(
        modifier
            .fillMaxSize()
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            ErrorSection(
                modifier = Modifier.fillMaxSize(),
                error = state.error,
                onEvent = { onEvent(TvContract.TvsEvent.Retry) }
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.item_spacing))
            ) {
                items(state.tvs) { tv ->
                    TvCard(
                        tv = tv,
                        modifier = modifier,
                        onEvent = { event -> onEvent(event) }
                    )
                }
            }
        }
    }
}