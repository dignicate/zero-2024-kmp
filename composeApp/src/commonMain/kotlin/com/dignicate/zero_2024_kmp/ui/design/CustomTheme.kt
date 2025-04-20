package com.dignicate.zero_2024_kmp.ui.design

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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
import androidx.compose.ui.unit.sp

fun lightExColors(
    appBarBackground: Color = Color(0xFF121212),
    appBarText: Color = Color(0xFFFFFFFF),
    textMain: Color = Color(0xFF121212),
    textSub: Color = Color(0xFF666666),
    indicator: Color = Color(0xFF666666),
): ExColors = ExColors(
    preset = lightColorScheme(),
    appBarBackground = appBarBackground,
    appBarText = appBarText,
    textMain = textMain,
    textSub = textSub,
    indicator = indicator,
)

fun darkExColors(
    appBarBackground: Color = Color(0xFFFFFFFF),
    appBarText: Color = Color(0xFF121212),
    textMain: Color = Color(0xFFEEEEEE),
    textSub : Color = Color(0xFFAAAAAA),
    indicator: Color = Color(0xFFAAAAAA),
): ExColors = ExColors(
    preset = darkColorScheme(),
    appBarBackground = appBarBackground,
    appBarText = appBarText,
    textMain = textMain,
    textSub = textSub,
    indicator = indicator,
)

@Stable
class ExColors(
    var preset: ColorScheme,
    appBarBackground: Color,
    appBarText: Color,
    textMain: Color,
    textSub: Color,
    indicator: Color,
) {
    var appBarBackground by mutableStateOf(appBarBackground, structuralEqualityPolicy())
        internal set
    var appBarText by mutableStateOf(appBarText, structuralEqualityPolicy())
        internal set
    var textMain by mutableStateOf(textMain, structuralEqualityPolicy())
        internal set
    var textSub by mutableStateOf(textSub, structuralEqualityPolicy())
        internal set
    var indicator by mutableStateOf(indicator, structuralEqualityPolicy())
        internal set

    fun copy(
        appBarBackground: Color = this.appBarBackground,
        appBarText: Color = this.appBarText,
        textMain: Color = this.textMain,
        textSub: Color = this.textSub,
        indicator: Color = this.indicator,
    ): ExColors = ExColors(
        preset = preset,
        appBarBackground = appBarBackground,
        appBarText = appBarText,
        textMain = textMain,
        textSub = textSub,
        indicator = indicator,
    )
}

internal fun ExColors.updateColorsFrom(other: ExColors) {
    preset = other.preset
    appBarBackground = other.appBarBackground
    appBarText = other.appBarText
    textMain = other.textMain
}

@Immutable
class ExTypography internal constructor(
    val preset: Typography,
    val itemMain: TextStyle,
    val itemSub: TextStyle,
) {
    constructor() : this(
        preset = Typography(),
        itemMain = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        ),
        itemSub = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
    )
}

internal val LocalExTypography = staticCompositionLocalOf { ExTypography() }

@Composable
fun MyCustomTheme(
    exColors: ExColors,
    exTypography: ExTypography = ExTypography(),
    content: @Composable () -> Unit,
) {
    val rememberedExColors = remember {
        exColors.copy()
    }.apply { updateColorsFrom(exColors) }
    CompositionLocalProvider(
        LocalExColors provides rememberedExColors,
        LocalExTypography provides exTypography,
    ) {
        MaterialTheme(
            colorScheme = exColors.preset,
            typography = exTypography.preset,
            content = content
        )
    }
}

object MyCustomTheme {
    val exColors: ExColors
        @Composable
        @ReadOnlyComposable
        get() = LocalExColors.current

    val exTypography: ExTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalExTypography.current
}

internal val LocalExColors = staticCompositionLocalOf { lightExColors() }

