package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun MyTabPager(
    tabs: List<String>,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    content: @Composable (Int) -> Unit
)
//{
//    Column {
//        Row(Modifier.fillMaxWidth()) {
//            tabs.forEachIndexed { index, title ->
//                Button(
//                    onClick = { onPageSelected(index) },
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Text(title)
//                }
//            }
//        }
//        Box(modifier = Modifier.fillMaxSize()) {
//            content(currentPage)
//        }
//    }
//}

enum class ViewState {
    Tabs
    ;
//    Settings,
//    Login

    fun mapToSwiftViewState(viewState: ViewState): String = when (viewState) {
        Tabs -> "tabs"
//        ViewState.Settings -> "settings"
//        ViewState.Login -> "login"
    }
}

public class ViewStateProvider {
    public fun startUpViewState(): String = "tabs" // ← Swift 側に渡すための文字列
}
