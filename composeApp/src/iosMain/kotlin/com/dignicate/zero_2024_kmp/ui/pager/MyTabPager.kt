package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme

@Composable
actual fun MyTabPager(
    tabs: List<String>,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    content: @Composable (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = currentPage,
            containerColor = MyCustomTheme.exColors.tabBackground,
            contentColor = MyCustomTheme.exColors.tabText,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[currentPage])
                        .height(4.dp),
                    color = MyCustomTheme.exColors.tabText
                )
            }
        ) {
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
        ) {
            content(currentPage)
        }
    }
}
