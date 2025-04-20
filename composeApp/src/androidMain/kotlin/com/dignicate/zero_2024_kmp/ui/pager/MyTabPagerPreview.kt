package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme
import com.dignicate.zero_2024_kmp.ui.design.darkExColors
import com.dignicate.zero_2024_kmp.ui.design.lightExColors

@Preview
@Composable
private fun MyTabPagerPreview_Light() {
    val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3")
    var currentPage by remember { mutableIntStateOf(0) }

    MyCustomTheme(
        exColors = lightExColors(),
    ) {
        MyTabPager(
            tabs = tabTitles,
            currentPage = currentPage,
            onPageSelected = { currentPage = it }
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Content for ${tabTitles[page]}")
            }
        }
    }
}

@Preview
@Composable
private fun MyTabPagerPreview_Dark() {
    val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3")
    var currentPage by remember { mutableIntStateOf(0) }

    MyCustomTheme(
        exColors = darkExColors(),
    ) {
        MyTabPager(
            tabs = tabTitles,
            currentPage = currentPage,
            onPageSelected = { currentPage = it }
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Content for ${tabTitles[page]}")
            }
        }
    }
}