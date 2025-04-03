package com.dignicate.zero_2024_kmp.ui.pager

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun PagerScreenViewController(index: Int): UIViewController {
    return ComposeUIViewController {
        PagerScreen(index)
    }
}
