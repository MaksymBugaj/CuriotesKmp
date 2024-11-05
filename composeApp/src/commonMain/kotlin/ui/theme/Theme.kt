package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import ui.theme.AppColors.backgroundDark
import ui.theme.AppColors.backgroundDarkHighContrast
import ui.theme.AppColors.backgroundDarkMediumContrast
import ui.theme.AppColors.backgroundLight
import ui.theme.AppColors.backgroundLightHighContrast
import ui.theme.AppColors.backgroundLightMediumContrast
import ui.theme.AppColors.errorContainerDark
import ui.theme.AppColors.errorContainerDarkHighContrast
import ui.theme.AppColors.errorContainerDarkMediumContrast
import ui.theme.AppColors.errorContainerLight
import ui.theme.AppColors.errorContainerLightHighContrast
import ui.theme.AppColors.errorContainerLightMediumContrast
import ui.theme.AppColors.errorDark
import ui.theme.AppColors.errorDarkHighContrast
import ui.theme.AppColors.errorDarkMediumContrast
import ui.theme.AppColors.errorLight
import ui.theme.AppColors.errorLightHighContrast
import ui.theme.AppColors.errorLightMediumContrast
import ui.theme.AppColors.inverseOnSurfaceDark
import ui.theme.AppColors.inverseOnSurfaceDarkHighContrast
import ui.theme.AppColors.inverseOnSurfaceDarkMediumContrast
import ui.theme.AppColors.inverseOnSurfaceLight
import ui.theme.AppColors.inverseOnSurfaceLightHighContrast
import ui.theme.AppColors.inverseOnSurfaceLightMediumContrast
import ui.theme.AppColors.inversePrimaryDark
import ui.theme.AppColors.inversePrimaryDarkHighContrast
import ui.theme.AppColors.inversePrimaryDarkMediumContrast
import ui.theme.AppColors.inversePrimaryLight
import ui.theme.AppColors.inversePrimaryLightHighContrast
import ui.theme.AppColors.inversePrimaryLightMediumContrast
import ui.theme.AppColors.inverseSurfaceDark
import ui.theme.AppColors.inverseSurfaceDarkHighContrast
import ui.theme.AppColors.inverseSurfaceDarkMediumContrast
import ui.theme.AppColors.inverseSurfaceLight
import ui.theme.AppColors.inverseSurfaceLightHighContrast
import ui.theme.AppColors.inverseSurfaceLightMediumContrast
import ui.theme.AppColors.onBackgroundDark
import ui.theme.AppColors.onBackgroundDarkHighContrast
import ui.theme.AppColors.onBackgroundDarkMediumContrast
import ui.theme.AppColors.onBackgroundLight
import ui.theme.AppColors.onBackgroundLightHighContrast
import ui.theme.AppColors.onBackgroundLightMediumContrast
import ui.theme.AppColors.onErrorContainerDark
import ui.theme.AppColors.onErrorContainerDarkHighContrast
import ui.theme.AppColors.onErrorContainerDarkMediumContrast
import ui.theme.AppColors.onErrorContainerLight
import ui.theme.AppColors.onErrorContainerLightHighContrast
import ui.theme.AppColors.onErrorContainerLightMediumContrast
import ui.theme.AppColors.onErrorDark
import ui.theme.AppColors.onErrorDarkHighContrast
import ui.theme.AppColors.onErrorDarkMediumContrast
import ui.theme.AppColors.onErrorLight
import ui.theme.AppColors.onErrorLightHighContrast
import ui.theme.AppColors.onErrorLightMediumContrast
import ui.theme.AppColors.onPrimaryContainerDark
import ui.theme.AppColors.onPrimaryContainerDarkHighContrast
import ui.theme.AppColors.onPrimaryContainerDarkMediumContrast
import ui.theme.AppColors.onPrimaryContainerLight
import ui.theme.AppColors.onPrimaryContainerLightHighContrast
import ui.theme.AppColors.onPrimaryContainerLightMediumContrast
import ui.theme.AppColors.onPrimaryDark
import ui.theme.AppColors.onPrimaryDarkHighContrast
import ui.theme.AppColors.onPrimaryDarkMediumContrast
import ui.theme.AppColors.onPrimaryLight
import ui.theme.AppColors.onPrimaryLightHighContrast
import ui.theme.AppColors.onPrimaryLightMediumContrast
import ui.theme.AppColors.onSecondaryContainerDark
import ui.theme.AppColors.onSecondaryContainerDarkHighContrast
import ui.theme.AppColors.onSecondaryContainerDarkMediumContrast
import ui.theme.AppColors.onSecondaryContainerLight
import ui.theme.AppColors.onSecondaryContainerLightHighContrast
import ui.theme.AppColors.onSecondaryContainerLightMediumContrast
import ui.theme.AppColors.onSecondaryDark
import ui.theme.AppColors.onSecondaryDarkHighContrast
import ui.theme.AppColors.onSecondaryDarkMediumContrast
import ui.theme.AppColors.onSecondaryLight
import ui.theme.AppColors.onSecondaryLightHighContrast
import ui.theme.AppColors.onSecondaryLightMediumContrast
import ui.theme.AppColors.onSurfaceDark
import ui.theme.AppColors.onSurfaceDarkHighContrast
import ui.theme.AppColors.onSurfaceDarkMediumContrast
import ui.theme.AppColors.onSurfaceLight
import ui.theme.AppColors.onSurfaceLightHighContrast
import ui.theme.AppColors.onSurfaceLightMediumContrast
import ui.theme.AppColors.onSurfaceVariantDark
import ui.theme.AppColors.onSurfaceVariantDarkHighContrast
import ui.theme.AppColors.onSurfaceVariantDarkMediumContrast
import ui.theme.AppColors.onSurfaceVariantLight
import ui.theme.AppColors.onSurfaceVariantLightHighContrast
import ui.theme.AppColors.onSurfaceVariantLightMediumContrast
import ui.theme.AppColors.onTertiaryContainerDark
import ui.theme.AppColors.onTertiaryContainerDarkHighContrast
import ui.theme.AppColors.onTertiaryContainerDarkMediumContrast
import ui.theme.AppColors.onTertiaryContainerLight
import ui.theme.AppColors.onTertiaryContainerLightHighContrast
import ui.theme.AppColors.onTertiaryContainerLightMediumContrast
import ui.theme.AppColors.onTertiaryDark
import ui.theme.AppColors.onTertiaryDarkHighContrast
import ui.theme.AppColors.onTertiaryDarkMediumContrast
import ui.theme.AppColors.onTertiaryLight
import ui.theme.AppColors.onTertiaryLightHighContrast
import ui.theme.AppColors.onTertiaryLightMediumContrast
import ui.theme.AppColors.outlineDark
import ui.theme.AppColors.outlineDarkHighContrast
import ui.theme.AppColors.outlineDarkMediumContrast
import ui.theme.AppColors.outlineLight
import ui.theme.AppColors.outlineLightHighContrast
import ui.theme.AppColors.outlineLightMediumContrast
import ui.theme.AppColors.outlineVariantDark
import ui.theme.AppColors.outlineVariantDarkHighContrast
import ui.theme.AppColors.outlineVariantDarkMediumContrast
import ui.theme.AppColors.outlineVariantLight
import ui.theme.AppColors.outlineVariantLightHighContrast
import ui.theme.AppColors.outlineVariantLightMediumContrast
import ui.theme.AppColors.primaryContainerDark
import ui.theme.AppColors.primaryContainerDarkHighContrast
import ui.theme.AppColors.primaryContainerDarkMediumContrast
import ui.theme.AppColors.primaryContainerLight
import ui.theme.AppColors.primaryContainerLightHighContrast
import ui.theme.AppColors.primaryContainerLightMediumContrast
import ui.theme.AppColors.primaryDark
import ui.theme.AppColors.primaryDarkHighContrast
import ui.theme.AppColors.primaryDarkMediumContrast
import ui.theme.AppColors.primaryLight
import ui.theme.AppColors.primaryLightHighContrast
import ui.theme.AppColors.primaryLightMediumContrast
import ui.theme.AppColors.scrimDark
import ui.theme.AppColors.scrimDarkHighContrast
import ui.theme.AppColors.scrimDarkMediumContrast
import ui.theme.AppColors.scrimLight
import ui.theme.AppColors.scrimLightHighContrast
import ui.theme.AppColors.scrimLightMediumContrast
import ui.theme.AppColors.secondaryContainerDark
import ui.theme.AppColors.secondaryContainerDarkHighContrast
import ui.theme.AppColors.secondaryContainerDarkMediumContrast
import ui.theme.AppColors.secondaryContainerLight
import ui.theme.AppColors.secondaryContainerLightHighContrast
import ui.theme.AppColors.secondaryContainerLightMediumContrast
import ui.theme.AppColors.secondaryDark
import ui.theme.AppColors.secondaryDarkHighContrast
import ui.theme.AppColors.secondaryDarkMediumContrast
import ui.theme.AppColors.secondaryLight
import ui.theme.AppColors.secondaryLightHighContrast
import ui.theme.AppColors.secondaryLightMediumContrast
import ui.theme.AppColors.surfaceBrightDark
import ui.theme.AppColors.surfaceBrightDarkHighContrast
import ui.theme.AppColors.surfaceBrightDarkMediumContrast
import ui.theme.AppColors.surfaceBrightLight
import ui.theme.AppColors.surfaceBrightLightHighContrast
import ui.theme.AppColors.surfaceBrightLightMediumContrast
import ui.theme.AppColors.surfaceContainerDark
import ui.theme.AppColors.surfaceContainerDarkHighContrast
import ui.theme.AppColors.surfaceContainerDarkMediumContrast
import ui.theme.AppColors.surfaceContainerHighDark
import ui.theme.AppColors.surfaceContainerHighDarkHighContrast
import ui.theme.AppColors.surfaceContainerHighDarkMediumContrast
import ui.theme.AppColors.surfaceContainerHighLight
import ui.theme.AppColors.surfaceContainerHighLightHighContrast
import ui.theme.AppColors.surfaceContainerHighLightMediumContrast
import ui.theme.AppColors.surfaceContainerHighestDark
import ui.theme.AppColors.surfaceContainerHighestDarkHighContrast
import ui.theme.AppColors.surfaceContainerHighestDarkMediumContrast
import ui.theme.AppColors.surfaceContainerHighestLight
import ui.theme.AppColors.surfaceContainerHighestLightHighContrast
import ui.theme.AppColors.surfaceContainerHighestLightMediumContrast
import ui.theme.AppColors.surfaceContainerLight
import ui.theme.AppColors.surfaceContainerLightHighContrast
import ui.theme.AppColors.surfaceContainerLightMediumContrast
import ui.theme.AppColors.surfaceContainerLowDark
import ui.theme.AppColors.surfaceContainerLowDarkHighContrast
import ui.theme.AppColors.surfaceContainerLowDarkMediumContrast
import ui.theme.AppColors.surfaceContainerLowLight
import ui.theme.AppColors.surfaceContainerLowLightHighContrast
import ui.theme.AppColors.surfaceContainerLowLightMediumContrast
import ui.theme.AppColors.surfaceContainerLowestDark
import ui.theme.AppColors.surfaceContainerLowestDarkHighContrast
import ui.theme.AppColors.surfaceContainerLowestDarkMediumContrast
import ui.theme.AppColors.surfaceContainerLowestLight
import ui.theme.AppColors.surfaceContainerLowestLightHighContrast
import ui.theme.AppColors.surfaceContainerLowestLightMediumContrast
import ui.theme.AppColors.surfaceDark
import ui.theme.AppColors.surfaceDarkHighContrast
import ui.theme.AppColors.surfaceDarkMediumContrast
import ui.theme.AppColors.surfaceDimDark
import ui.theme.AppColors.surfaceDimDarkHighContrast
import ui.theme.AppColors.surfaceDimDarkMediumContrast
import ui.theme.AppColors.surfaceDimLight
import ui.theme.AppColors.surfaceDimLightHighContrast
import ui.theme.AppColors.surfaceDimLightMediumContrast
import ui.theme.AppColors.surfaceLight
import ui.theme.AppColors.surfaceLightHighContrast
import ui.theme.AppColors.surfaceLightMediumContrast
import ui.theme.AppColors.surfaceVariantDark
import ui.theme.AppColors.surfaceVariantDarkHighContrast
import ui.theme.AppColors.surfaceVariantDarkMediumContrast
import ui.theme.AppColors.surfaceVariantLight
import ui.theme.AppColors.surfaceVariantLightHighContrast
import ui.theme.AppColors.surfaceVariantLightMediumContrast
import ui.theme.AppColors.tertiaryContainerDark
import ui.theme.AppColors.tertiaryContainerDarkHighContrast
import ui.theme.AppColors.tertiaryContainerDarkMediumContrast
import ui.theme.AppColors.tertiaryContainerLight
import ui.theme.AppColors.tertiaryContainerLightHighContrast
import ui.theme.AppColors.tertiaryContainerLightMediumContrast
import ui.theme.AppColors.tertiaryDark
import ui.theme.AppColors.tertiaryDarkHighContrast
import ui.theme.AppColors.tertiaryDarkMediumContrast
import ui.theme.AppColors.tertiaryLight
import ui.theme.AppColors.tertiaryLightHighContrast
import ui.theme.AppColors.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkScheme
    } else {
        lightScheme
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )

}