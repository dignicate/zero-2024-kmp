package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AutomobileCompanyListScreen(
    modifier: Modifier,
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