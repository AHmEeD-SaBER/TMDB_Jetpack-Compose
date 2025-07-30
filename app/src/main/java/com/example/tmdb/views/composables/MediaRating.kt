package com.example.tmdb.views.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Typography


@SuppressLint("DefaultLocale")
@Composable
fun MediaRating(modifier: Modifier = Modifier, rating: Double, voteCount: Int) {
    val rounded = String.format("%.1f", rating)
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        Icon(
            painter = painterResource(R.drawable.rating_icon),
            contentDescription = stringResource(R.string.rating_icon),
            tint = Color(0xFFFFC219) // Keeping star color yellow in both themes
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_5)),
            text = "${rounded}/10  IMDb (${voteCount})",
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        )

    }
}