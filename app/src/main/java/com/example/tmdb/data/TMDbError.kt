package com.example.tmdb.data

import androidx.annotation.DrawableRes
import com.example.tmdb.R
import com.example.tmdb.utils.Constants

sealed class TMDbError(
    @DrawableRes val drawableResource: Int = R.drawable.ic_launcher_background,
    val title: String,
    val subtitle: String
) : Error() {
    data class NetworkError(
        override val message: String
    ) : TMDbError(
        drawableResource = R.drawable.no_internet,
        title = Constants.ErrorTitles.NETWORK_ERROR,
        subtitle = message
    )
    data class DataError(
        override val message: String
    ) : TMDbError(
        drawableResource = R.drawable.no_data,
        title = Constants.ErrorTitles.DATA_ERROR,
        subtitle = message
    )
    data class UnknownError(
        override val message: String
    ) : TMDbError(
        drawableResource = R.drawable.rejected,
        title = Constants.ErrorTitles.UNKNOWN_ERROR,
        subtitle = message
    )
}
