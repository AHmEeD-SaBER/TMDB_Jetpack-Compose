package com.example.tmdb.views.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.tmdb.R
import com.example.tmdb.data.moviedetails.ProductionCompany
import com.example.tmdb.ui.theme.Typography


@Composable
fun MediaDetailsInfo(
    modifier: Modifier = Modifier,
    languages: List<String>,
    companies: List<ProductionCompany>
) {
    Text(
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_10)),
        text = "Languages",
        style = Typography.titleLarge.copy(fontSize = 18.sp)
    )

    LazyRow(
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_10)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_5))
    ) {
        items(languages) { lang ->
            MediaTag(
                name = lang
            )
        }
    }

    Text(
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_10)),
        text = "Production Companies",
        style = Typography.titleLarge.copy(fontSize = 18.sp)
    )

    LazyRow(
        modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_10)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_5))
    ) {
        items(companies) { company ->
            MediaTag(
                name = company.name
            )
        }
    }


}