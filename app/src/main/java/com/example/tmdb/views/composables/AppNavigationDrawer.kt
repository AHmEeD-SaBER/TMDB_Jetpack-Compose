import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Typography


@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()

            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_50)),
            text = "TMDb App",
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    items: List<DrawerItem>,
    onItemClick: (DrawerItem) -> Unit
) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(items) { item ->
            DrawerItemView(
                item = item,
                onClick = { onItemClick(item) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DrawerItemView(modifier: Modifier = Modifier, item: DrawerItem, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
    }
}

data class DrawerItem(
    val id: Int,
    val title: String,
    val icon: Int = R.drawable.home_icon,
)