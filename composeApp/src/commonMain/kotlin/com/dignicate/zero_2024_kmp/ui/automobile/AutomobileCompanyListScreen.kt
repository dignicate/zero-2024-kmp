package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun AutomobileCompanyListScreen(
    modifier: Modifier,
    viewModel: AutomobileCompanyListViewModel = getKoin().get(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Companies") },
            )
        },
        content = {
            Text("Content goes here")
        },
    )
}