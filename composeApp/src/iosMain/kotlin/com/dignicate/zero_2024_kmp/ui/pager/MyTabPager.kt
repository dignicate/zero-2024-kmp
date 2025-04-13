package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
actual fun MyTabPager(
    tabs: List<String>,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    content: @Composable (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = currentPage == index,
                    onClick = { onPageSelected(index) },
                    text = { Text(title) }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            content(currentPage)
        }
    }
}
