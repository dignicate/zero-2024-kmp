package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dignicate.zero_2024_kmp.ui.pager.pages.Page2
import com.dignicate.zero_2024_kmp.ui.pager.pages.Page3
import com.dignicate.zero_2024_kmp.ui.screen.automobile.AutomobileCompanyListScreen

@Composable
fun PagerScreen(
    rootNavController: NavController,
    paddingValues: PaddingValues,
    index: Int,
) {
    when (index) {
        0 -> AutomobileCompanyListScreen(
            rootNavController = rootNavController,
            modifier = Modifier.padding(paddingValues),
        )
        1 -> Page2()
        2 -> Page3()
        else -> Text("Invalid page")
    }
}
