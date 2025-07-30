package com.example.tmdb.views.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.tmdb.R

@Composable
fun RateLengthRow(modifier: Modifier = Modifier, rating: Double, voteCount: Int, runtime: Int) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        MediaRating(
            modifier = Modifier,
            rating = rating,
            voteCount = voteCount
        )
        Text(
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.padding_25)),
            text = "Length: $runtime min",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}