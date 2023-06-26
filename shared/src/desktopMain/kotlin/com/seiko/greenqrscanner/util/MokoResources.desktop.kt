package com.seiko.greenqrscanner.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import dev.icerock.moko.resources.StringResource

@Composable
@ReadOnlyComposable
actual fun stringResource(resource: StringResource): String {
    return resource.localized()
}
