package com.example.tmdb.views.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.tmdb.R
import com.example.tmdb.data.MediaItem
import com.example.tmdb.ui.theme.Typography


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MediaItemInfo(
    modifier: Modifier = Modifier,
    mediaItem: MediaItem,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(R.dimen.padding_15),
            ), verticalArrangement = Arrangement.SpaceEvenly
    ) {
        MediaTitle(
            title = mediaItem.title,
            animatedVisibilityScope = animatedVisibilityScope
        )
        Text(
            text = "Release Date: ${mediaItem.releaseDate}",
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        MediaRating(
            modifier = Modifier.fillMaxWidth(),
            rating = mediaItem.vote_average,
            voteCount = mediaItem.vote_count
        )
    }
}
