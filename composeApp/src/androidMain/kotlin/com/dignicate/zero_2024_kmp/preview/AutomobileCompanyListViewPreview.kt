package com.dignicate.zero_2024_kmp.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dignicate.zero_2024_kmp.ui.automobile.AutomobileCompanyListView
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme
import com.dignicate.zero_2024_kmp.ui.design.darkExColors
import com.dignicate.zero_2024_kmp.ui.design.lightExColors

@Preview
@Composable
private fun AutomobileCompanyListViewPreview() {
    MyCustomTheme(
        exColors = lightExColors(),
    ) {
        AutomobileCompanyListView(
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun AutomobileCompanyListViewPreview_Dark() {
    MyCustomTheme(
        exColors = darkExColors(),
    ) {
        AutomobileCompanyListView(
            modifier = Modifier,
        )
    }
}
