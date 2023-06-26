package com.seiko.greenqrscanner.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
@ReadOnlyComposable
actual fun stringResource(resource: StringResource): String {
    return StringDesc.Resource(resource).localized()
}
