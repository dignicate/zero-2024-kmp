package com.dignicate.zero_2024_kmp.ui.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*

object Graph {
    const val NAVIGATION_BAR_SCREEN_GRAPH = "navigationBarScreenGraph"
}

sealed class Routes(var route: String) {
    data object Test : Routes("test")
    data object Home : Routes("home")
    data object Setting : Routes("setting")
    data object HomeDetail : Routes("homeDetail")
    data object SettingDetail : Routes("settingDetail")
}

val navigationItemsLists = listOf(
    NavigationItem(
        unSelectedIcon = Icons.Outlined.Build,
        selectedIcon = Icons.Filled.Build,
        title = "Test",
        route = Routes.Test.route,
    ),
    NavigationItem(
        unSelectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        title = "Home",
        route = Routes.Home.route,
    ),
    NavigationItem(
        unSelectedIcon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search,
        title = "Setting",
        route = Routes.Setting.route,
    ),
)