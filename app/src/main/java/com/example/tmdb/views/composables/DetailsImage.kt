package com.example.tmdb.views.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.tmdb.R
import com.example.tmdb.data.getFullPosterPath
import com.example.tmdb.utils.Constants

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsImage(
    modifier: Modifier = Modifier,
    posterPath: String,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.details_image_height))
    ) {
        AsyncImage(
            model = posterPath.getFullPosterPath,
            placeholder = painterResource(R.drawable.image_placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .sharedElement(
                    rememberSharedContentState(key = posterPath),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_radius))),
            contentScale = ContentScale.Crop,
        )

//        AnimatedVisibility(
//            visible = true,
//            enter = fadeIn(animationSpec = tween(durationMillis = Constants.DEFAULT_ANIMATION_DURATION)),
//            exit = fadeOut(animationSpec = tween(durationMillis = Constants.DEFAULT_ANIMATION_DURATION))
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_radius)))
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Transparent,
//                                Color.Black
//                            )
//                        )
//                    )
//            )
//        }

    }
}