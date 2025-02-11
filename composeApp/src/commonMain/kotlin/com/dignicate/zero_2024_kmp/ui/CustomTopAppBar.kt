package com.dignicate.zero_2024_kmp.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    text: String,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    )
}
