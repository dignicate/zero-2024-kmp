package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
actual fun MyTabPager(
    tabs: List<String>,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    content: @Composable (Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = currentPage)
    val coroutineScope = rememberCoroutineScope()

    // 外部からの page sync
    LaunchedEffect(currentPage) {
        if (pagerState.currentPage != currentPage) {
            pagerState.scrollToPage(currentPage)
        }
    }

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MyCustomTheme.exColors.tabBackground,
            contentColor = MyCustomTheme.exColors.tabText,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(4.dp),
                    color = MyCustomTheme.exColors.tabText
                )
            },
            // optional: ripple + indicator customizationも可能
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = title) }
                )
            }
        }

        HorizontalPager(
            count = tabs.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            content(page)
        }
    }

    // pager から currentPage を更新
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { onPageSelected(it) }
    }
}


