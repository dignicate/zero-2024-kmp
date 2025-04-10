package com.dignicate.zero_2024_kmp.ui.screen.home

import androidx.compose.ui.graphics.vector.ImageVector

// https://proandroiddev.com/how-to-integrate-bottom-navigation-bar-for-compact-screens-and-a-navigation-rail-for-larger-screens-c7dc3baab0e7

data class NavigationItem(
    val unSelectedIcon: ImageVector /* or  DrawableResource*/,
    val selectedIcon: ImageVector /* or  DrawableResource*/,
    val title: String /* or  StringResource  */,
    val route: String
)
