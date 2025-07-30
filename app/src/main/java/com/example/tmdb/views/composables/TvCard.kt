package com.example.tmdb.views.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tmdb.contracts.TvContract
import com.example.tmdb.data.asMediaItem
import com.example.tmdb.data.tv.Tv

@Composable
fun TvCard(modifier: Modifier = Modifier, tv: Tv, onEvent: (TvContract.TvsEvent) -> Unit = {}) {
    MediaItemCard(
        modifier = modifier,
        mediaItem = tv.asMediaItem,
        onItemClicked = { id -> onEvent(TvContract.TvsEvent.TvClicked(id)) },
    )
}
