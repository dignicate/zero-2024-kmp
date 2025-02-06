package com.dignicate.zero_2024_kmp.ui.spla

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BukiListScreen(
    modifier: Modifier,
//    viewModel: BukiListViewModel = getKoin().get(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Buki List") }
            )
        },
        content = {
            Text("Content goes here")
        },
    )
}

