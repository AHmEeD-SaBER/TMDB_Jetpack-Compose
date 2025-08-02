package com.example.tmdb.views.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.tmdb.R
import com.example.tmdb.data.MediaItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MediaItemCard(
    modifier: Modifier = Modifier,
    mediaItem: MediaItem,
    onItemClicked: (Int) -> Unit = {},
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked(mediaItem.id)
            }
            .height(dimensionResource(R.dimen.card_height))
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius))
            ), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start
    ) {
        sharedTransitionScope.MediaItemImage(
            posterPath = mediaItem.poster_path,
            contentDesc = mediaItem.title,
            animatedVisibilityScope = animatedVisibilityScope
        )
        sharedTransitionScope.MediaItemInfo(
            mediaItem = mediaItem,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}
