package com.dignicate.zero_2024_kmp.ui.screen

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dignicate.zero_2024_kmp.ui.pager.MyTabPager

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
            TabContent(page = page)
        }
    }
}

@Composable
private fun TabContent(page: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "This is page ${page + 1}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
