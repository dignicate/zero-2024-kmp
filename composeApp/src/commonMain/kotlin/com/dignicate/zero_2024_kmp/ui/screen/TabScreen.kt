package com.dignicate.zero_2024_kmp.ui.screen

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dignicate.zero_2024_kmp.ui.pager.MyTabPager
import com.dignicate.zero_2024_kmp.ui.pager.PagerScreen
import getPlatform
import isIos

@Composable
fun TabScreen() {
    val tabTitles = listOf("First", "Second", "Third")
    var currentPage by remember { mutableStateOf(0) }

    if (getPlatform().isIos()) {
        // iOS → Swift 側に任せる or シンプル表示
//        TabContent(page = currentPage)
    } else {
        // Android / Desktop → Column で safe area 避けてレイアウト
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
//                TabContent(page = page)
                PagerScreen(page)
            }
        }
    }
}


//@Composable
//private fun TabContent(page: Int) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(32.dp)
//    ) {
//        Text(
//            text = "This is page ${page + 1}",
//            style = MaterialTheme.typography.headlineMedium
//        )
//    }
//}
