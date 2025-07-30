package com.example.tmdb.contracts

import DrawerItem
import com.example.tmdb.R
import com.example.tmdb.navigation.Routes

class MainContract {
    data class MainState(
        val isDrawerOpen: Boolean = false,
        val currentRoute: String = Routes.HOME,
        val drawerItems: List<DrawerItem> = listOf(
            DrawerItem(id = 1, title = "Movies", icon = R.drawable.movie),
            DrawerItem(id = 2, title = "Tv Shows", icon = R.drawable.tv),
            DrawerItem(id = 3, title = "Settings", icon = R.drawable.settings_icon),
        )
    )

    sealed class MainEvent {
        data class DrawerItemClicked(val itemId: Int) : MainEvent()
        data object OpenDrawer : MainEvent()
        data object CloseDrawer : MainEvent()
    }
}