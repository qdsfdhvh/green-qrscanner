package com.seiko.greenqrscanner.util

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

@OptIn(ExperimentalResourceApi::class)
@Composable
inline fun stringResource(resource: StringResource): String {
    return getString(resource)
}
