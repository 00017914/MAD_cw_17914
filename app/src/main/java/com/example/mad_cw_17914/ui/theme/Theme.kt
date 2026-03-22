package com.example.mad_cw_17914.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Forced Light Scheme for consistent VIVA presentation
private val PinkColorScheme = lightColorScheme(
    primary = PinkPrimary,
    secondary = PinkSecondary,
    tertiary = PinkTertiary,
    background = PinkBackground,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F)
)

@Composable
fun MAD_CW_17914Theme(
    // We remove dynamicColor and darkTheme parameters to keep the brand consistent
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PinkColorScheme,
        typography = Typography,
        content = content
    )
}