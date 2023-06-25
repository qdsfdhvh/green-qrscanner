package com.seiko.greenqrscanner.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

internal val DarkColorPalette = darkColorScheme(
    primary = BlueSubtle,
    onPrimary = BlueDarker,
    primaryContainer = BlueDark,
    onPrimaryContainer = BlueLightest,
    inversePrimary = BlueMain,
    secondary = SubtleWarning,
    onSecondary = DarkestWarning,
    secondaryContainer = DarkerWarning,
    onSecondaryContainer = LightWarning,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = GreyDarkest,
    onBackground = GreySubtle,
    surface = GreyDark,
    onSurface = White,
    inverseSurface = GreyLight,
    inverseOnSurface = GreySubtle,
    surfaceVariant = GreyNormal,
    onSurfaceVariant = White,
    outline = GreySubtle,
)

internal val LightColorPalette = lightColorScheme(
    primary = BlueMain,
    onPrimary = BlueLightest,
    primaryContainer = BlueLight,
    onPrimaryContainer = BlueDark,
    inversePrimary = BlueSubtle,
    secondary = Warning,
    onSecondary = LightWarning,
    secondaryContainer = SubtleWarning,
    onSecondaryContainer = DarkWarning,
    error = RedMain,
    onError = Red90,
    errorContainer = Red80,
    onErrorContainer = Red20,
    background = BlueLightest,
    onBackground = GreyDark,
    surface = White,
    onSurface = GreyDark,
    inverseSurface = GreyNormal,
    inverseOnSurface = GreySubtle,
    surfaceVariant = White,
    onSurfaceVariant = GreyDark,
    outline = GreySubtle,
)
