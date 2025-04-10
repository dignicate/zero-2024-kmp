package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.runtime.Composable

@Composable
expect fun MyTabPager(
    tabs: List<String>,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    content: @Composable (Int) -> Unit
)
