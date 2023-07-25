package com.seiko.greenqrscanner.ui.widget

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.staticCompositionLocalOf
import com.seiko.greenqrscanner.util.AppDateFormatter

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass> {
    error("No WindowSizeClass available")
}

val LocalAppDateFormatter = staticCompositionLocalOf<AppDateFormatter> {
    error("AppDateFormatter not provided")
}
