package com.example.tmdb.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.tmdb.R

@Composable
fun MediaTag(modifier: Modifier = Modifier, name: String) {
    Box(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            shape = RoundedCornerShape(dimensionResource(R.dimen.large_corner_radius))
        )
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.padding_10),
                vertical = dimensionResource(R.dimen.padding_5)
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}