package com.example.tmdb.views.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Typography


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MediaTitle(
    modifier: Modifier = Modifier,
    title: String = "",
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Text(
        title,
        modifier = modifier
            .widthIn(max = dimensionResource(R.dimen.max_title_width))
            .sharedElement(
                rememberSharedContentState(key = "title_$title"),
                animatedVisibilityScope = animatedVisibilityScope
            ),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = Typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}
