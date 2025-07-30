package com.example.tmdb.views.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Typography

@Composable
fun MediaDescription(modifier: Modifier = Modifier, desc: String) {
    Text(
        modifier = modifier,
        text = "Description",
        style = Typography.titleLarge.copy(fontSize = 18.sp)
    )
    Text(
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_5)),
        text = desc,
        style = Typography.labelMedium,
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
    )
}