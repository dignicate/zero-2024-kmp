package com.dignicate.zero_2024_kmp.ui.screen

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.dignicate.zero_2024_kmp.ui.pager.MyTabPager
import com.dignicate.zero_2024_kmp.ui.pager.PagerScreen

@Composable
fun TabScreen() {
    val tabTitles = listOf("First", "Second", "Third")
    var currentPage by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        MyTabPager(
            tabs = tabTitles,
            currentPage = currentPage,
            onPageSelected = { currentPage = it }
        ) { page ->
            PagerScreen(page)
        }
    }
}
