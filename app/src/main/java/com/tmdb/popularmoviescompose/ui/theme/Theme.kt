package com.tmdb.popularmoviescompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColors = darkColors(
    primary = Navy,
    primaryVariant = Black,
    secondary = Teal200
)

private val LightColors = lightColors(
    primary = Navy,
    primaryVariant = Black,
    secondary = Teal200
)

@Composable
fun MovieTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}