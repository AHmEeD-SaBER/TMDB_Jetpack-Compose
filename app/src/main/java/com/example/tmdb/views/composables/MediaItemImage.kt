package com.example.tmdb.views.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.tmdb.R
import com.example.tmdb.data.getFullPosterPath


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MediaItemImage(
    modifier: Modifier = Modifier,
    posterPath: String = "",
    contentDesc: String = "",
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Surface(
        modifier = modifier
            .fillMaxHeight()
            .width(dimensionResource(R.dimen.poster_width)),
        shadowElevation = dimensionResource(R.dimen.elevation),
        tonalElevation = dimensionResource(R.dimen.elevation),
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius))
    ) {
        AsyncImage(
            model = posterPath.getFullPosterPath,
            placeholder = painterResource(R.drawable.image_placeholder),
            contentDescription = contentDesc,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_radius)))
                .sharedElement(
                    rememberSharedContentState(key = posterPath),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            contentScale = ContentScale.Crop
        )
    }

}