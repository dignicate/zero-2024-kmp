package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dignicate.zero_2024_kmp.ui.pager.pages.Page1
import com.dignicate.zero_2024_kmp.ui.pager.pages.Page2
import com.dignicate.zero_2024_kmp.ui.pager.pages.Page3

@Composable
fun PagerScreen(index: Int) {
    when (index) {
        0 -> Page1()
        1 -> Page2()
        2 -> Page3()
        else -> Text("Invalid page")
    }
}
