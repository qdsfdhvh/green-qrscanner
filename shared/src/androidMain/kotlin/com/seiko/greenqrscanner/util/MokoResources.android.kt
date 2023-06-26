package com.seiko.greenqrscanner.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.StringResource

@Composable
@ReadOnlyComposable
actual fun stringResource(resource: StringResource): String {
    val context = LocalContext.current
    return resource.getString(context)
}
