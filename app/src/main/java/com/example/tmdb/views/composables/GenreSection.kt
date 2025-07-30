package com.example.tmdb.views.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.tmdb.R
import com.example.tmdb.data.moviedetails.Genre

@Composable
fun GenreSection(modifier: Modifier = Modifier, items: List<Genre>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_5))
    ) {
        items(items) { genre ->
            MediaTag(
                name = genre.name
            )
        }
    }
}