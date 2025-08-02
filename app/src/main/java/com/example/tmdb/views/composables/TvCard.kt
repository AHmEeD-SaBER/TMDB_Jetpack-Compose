package com.example.tmdb.views.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tmdb.contracts.TvContract
import com.example.tmdb.data.asMediaItem
import com.example.tmdb.data.tv.Tv

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TvCard(
    modifier: Modifier = Modifier, tv: Tv, onEvent: (TvContract.TvsEvent) -> Unit = {},
    animatedVisibilityScope: AnimatedVisibilityScope, sharedTransitionScope: SharedTransitionScope
) {
    MediaItemCard(
        modifier = modifier,
        mediaItem = tv.asMediaItem,
        onItemClicked = { id -> onEvent(TvContract.TvsEvent.TvClicked(id)) },
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = sharedTransitionScope
    )
}
