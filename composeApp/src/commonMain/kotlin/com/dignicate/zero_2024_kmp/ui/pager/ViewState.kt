package com.dignicate.zero_2024_kmp.ui.pager

enum class ViewState {
    Tabs;

    fun mapToSwiftViewState(viewState: ViewState): String = when (viewState) {
        Tabs -> "tabs"
    }
}

public class ViewStateProvider {
    public fun startUpViewState(): String = "tabs" // ← Swift 側に渡すための文字列
}
