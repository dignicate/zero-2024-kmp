package com.dignicate.zero_2024_kmp.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dignicate.zero_2024_kmp.ui.automobile.*
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme
import com.dignicate.zero_2024_kmp.ui.design.darkExColors
import com.dignicate.zero_2024_kmp.ui.design.lightExColors
import com.dignicate.zero_2024_kmp.domain.automobile.Company

private val mockCompanyData = listOf(
    Company(id = Company.Id(1), name = "Sample Company 1", country = "Country 1", foundedYear = 2001),
    Company(id = Company.Id(2), name = "Sample Company 2", country = "Country 2", foundedYear = 2002),
    Company(id = Company.Id(3), name = "Sample Company 3", country = "Country 3", foundedYear = 2003)
)

@Preview
@Composable
private fun AutomobileCompanyListViewPreview() {
    MyCustomTheme(
        exColors = lightExColors(),
    ) {
        AutomobileCompanyListView(
            modifier = Modifier,
            data = mockCompanyData,
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
            data = mockCompanyData,
        )
    }
}

@Preview
@Composable
private fun AutomobileCompanyListItemViewPreview() {
    MyCustomTheme(
        exColors = lightExColors(),
    ) {
        AutomobileCompanyListItemView(
            modifier = Modifier,
            companyName = "Sample Company",
            country = "Sample Country",
            foundedYear = 2024,
        )
    }
}

@Preview
@Composable
private fun AutomobileCompanyListItemViewPreview_Dark() {
    MyCustomTheme(
        exColors = darkExColors(),
    ) {
        AutomobileCompanyListItemView(
            modifier = Modifier,
            companyName = "Sample Company",
            country = "Sample Country",
            foundedYear = 2024,
        )
    }
}

@Preview
@Composable
private fun AutomobileCompanyListItemViewPreview_LongText() {
    MyCustomTheme(
        exColors = lightExColors(),
    ) {
        AutomobileCompanyListItemView(
            modifier = Modifier,
            companyName = "Sample Company Very Very Very Long Long Name",
            country = "Sample Country",
            foundedYear = 2024,
        )
    }
}
