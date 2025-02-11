package com.dignicate.zero_2024_kmp.ui.appbar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    text: String,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier,
                text = text,
                color = MyCustomTheme.exColors.textMain,
            )
        },
        backgroundColor = MyCustomTheme.exColors.appBarBackground,
    )
}
