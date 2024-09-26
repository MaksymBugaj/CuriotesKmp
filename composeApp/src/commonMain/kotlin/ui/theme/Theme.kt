package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = AppColors.primary,
    primaryVariant = AppColors.primaryVariant,
    secondary = AppColors.secondary,
    background = AppColors.background,
    surface = AppColors.surface,
    error = AppColors.error,
    onPrimary = AppColors.onPrimary,
    onSecondary = AppColors.onSecondary,
    onBackground = AppColors.onBackground,
    onSurface = AppColors.onSurface,
    onError = AppColors.onError
)

private val LightColorPalette = lightColors(
    primary = AppColors.primary,
    primaryVariant = AppColors.primaryVariant,
    secondary = AppColors.secondary,
    background = AppColors.background,
    surface = AppColors.surface,
    error = AppColors.error,
    onPrimary = AppColors.onPrimary,
    onSecondary = AppColors.onSecondary,
    onBackground = AppColors.onBackground,
    onSurface = AppColors.onSurface,
    onError = AppColors.onError
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}