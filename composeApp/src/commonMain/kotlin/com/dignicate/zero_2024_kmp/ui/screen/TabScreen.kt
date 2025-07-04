package com.dignicate.zero_2024_kmp.ui.screen

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dignicate.zero_2024_kmp.ui.pager.MyTabPager
import com.dignicate.zero_2024_kmp.ui.pager.PagerScreen
import com.dignicate.zero_2024_kmp.util.logger
import org.lighthousegames.logging.logging

@Composable
fun TabScreen(
    rootNavController: NavController,
    paddingValues: PaddingValues,
) {
    val tabTitles = listOf("First", "Second", "Third")
    var currentPage by remember { mutableStateOf(0) }

    logger.d("TabScreen, paddingValues: $paddingValues")
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        MyTabPager(
            tabs = tabTitles,
            currentPage = currentPage,
            onPageSelected = { currentPage = it }
        ) { page ->
            PagerScreen(
                rootNavController = rootNavController,
                index = page
            )
        }
    }
}
