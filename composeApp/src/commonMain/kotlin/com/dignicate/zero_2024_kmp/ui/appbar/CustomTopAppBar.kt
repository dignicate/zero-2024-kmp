package com.dignicate.zero_2024_kmp.ui.appbar

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    text: String,
) {
    TopAppBar(
        modifier = modifier.statusBarsPadding(),
        title = {
            Text(
                modifier = Modifier,
                text = text,
                color = MyCustomTheme.exColors.appBarText,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MyCustomTheme.exColors.appBarBackground
        )
    )
}
