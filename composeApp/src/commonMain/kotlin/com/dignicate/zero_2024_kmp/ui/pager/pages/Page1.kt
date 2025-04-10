package com.dignicate.zero_2024_kmp.ui.pager.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Page1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFEBEE))
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "This is Page 1",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
