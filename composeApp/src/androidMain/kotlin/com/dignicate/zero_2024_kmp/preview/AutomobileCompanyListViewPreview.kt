package com.dignicate.zero_2024_kmp.preview

import MyCustomTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dignicate.zero_2024_kmp.ui.automobile.AutomobileCompanyListView

@Preview
@Composable
private fun AutomobileCompanyListViewPreview() {
    MyCustomTheme {
        AutomobileCompanyListView(
            modifier = Modifier,
        )
    }
}
