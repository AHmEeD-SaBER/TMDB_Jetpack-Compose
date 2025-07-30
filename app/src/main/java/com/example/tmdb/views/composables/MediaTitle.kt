package com.example.tmdb.views.composables

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Typography


@Composable
fun MediaTitle(modifier: Modifier = Modifier, title: String = "") {
    Text(
        title,
        modifier = modifier.widthIn(max = dimensionResource(R.dimen.max_title_width)),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = Typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

}