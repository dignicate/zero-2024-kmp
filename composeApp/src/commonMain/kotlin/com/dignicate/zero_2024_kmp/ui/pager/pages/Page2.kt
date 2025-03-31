package com.dignicate.zero_2024_kmp.ui.pager.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Page2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "This is Page 2",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
