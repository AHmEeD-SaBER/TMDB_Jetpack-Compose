package com.example.tmdb.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.tmdb.R
import com.example.tmdb.data.TMDbError
import com.example.tmdb.ui.theme.Typography

@Composable
fun ErrorSection(modifier: Modifier = Modifier, error: TMDbError, onEvent: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(error.drawableResource),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.error_image_size))
        )
        Text(
            text = error.title,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_15)),
            style = Typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Text(
            text = error.subtitle,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_5)),
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { onEvent() },
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_15))
        ) {
            Text(
                text = stringResource(R.string.retry),
                style = Typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}