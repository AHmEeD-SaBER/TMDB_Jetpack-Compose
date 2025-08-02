package com.example.tmdb.views.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Typography
import com.example.tmdb.contracts.MainContract

@Composable
fun CustomAppBar(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onEvent: (MainContract.MainEvent) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (showBackButton) {
                    onBackClick()
                } else {
                    Log.d("CustomAppBar", "Menu button clicked")
                    onEvent(MainContract.MainEvent.OpenDrawer)
                }
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(
                    if (showBackButton) R.drawable.ic_arrow_back else R.drawable.menu
                ),
                contentDescription = stringResource(
                    if (showBackButton) R.string.back_button_description else R.string.menu_icon_description
                ),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = title,
            modifier = Modifier,
            textAlign = TextAlign.Center,
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        IconButton(
            onClick = {},
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.bell),
                contentDescription = stringResource(R.string.notification_icon_description),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}