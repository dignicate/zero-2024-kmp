package com.dignicate.zero_2024_kmp.ui.design

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val LightColorPalette = lightColors(
//    primary = Color(0xFFEEEEEE),
//    secondary = Color(0xFF03DAC5),
//    background = Color.White,
//    onPrimary = Color(0xFF121212),
)

val DarkColorPalette = darkColors(
//    primary = Color(0xFFBB86FC),
//    secondary = Color(0xFF03DAC5),
//    background = Color.Black,
)

fun lightExColors(
    appBarBackground: Color = Color(0xFFEEEEEE),
    textMain: Color = Color(0xFF121212),
): ExColors = ExColors(
    preset = LightColorPalette,
    appBarBackground = appBarBackground,
    textMain = textMain,
)

fun darkExColors(
    appBarBackground: Color = Color(0xFF121212),
    textMain: Color = Color(0xFFEEEEEE),
): ExColors = ExColors(
    preset = DarkColorPalette,
    appBarBackground = appBarBackground,
    textMain = textMain,
)

@Stable
class ExColors(
    var preset: Colors,
    appBarBackground: Color,
    textMain: Color,
) {
    var appBarBackground by mutableStateOf(appBarBackground, structuralEqualityPolicy())
        internal set
    var textMain by mutableStateOf(textMain, structuralEqualityPolicy())
        internal set

    fun copy(
        appBarBackground: Color = this.appBarBackground,
        textMain: Color = this.textMain,
    ): ExColors = ExColors(
        preset = preset,
        appBarBackground = appBarBackground,
        textMain = textMain,
    )
}


internal fun ExColors.updateColorsFrom(other: ExColors) {
    preset = other.preset
    appBarBackground = other.appBarBackground
    textMain = other.textMain
}

@Immutable
class ExTypography internal constructor(
    val presetz: Typography,
    val itemMain: TextStyle,
    val itemSub: TextStyle,
) {
}

@Composable
fun MyCustomTheme(
    exColors: ExColors,
    content: @Composable () -> Unit,
) {
//    val colors = if (isSystemInDarkTheme()) {
//        logger.d("DarkColorPalette")
//        DarkColorPalette
//    } else {
//        logger.d("LightColorPalette")
//        LightColorPalette
//    }

    val rememberedExColors = remember {
        exColors.copy()
    }.apply { updateColorsFrom(exColors) }
    CompositionLocalProvider(
        LocalExColors provides rememberedExColors,
    ) {
        MaterialTheme(
            colors = exColors.preset,
            content = content
        )
    }
}

object MyCustomTheme {
    val exColors: ExColors
        @Composable
        @ReadOnlyComposable
        get() = LocalExColors.current
}

internal val LocalExColors = staticCompositionLocalOf { lightExColors() }
