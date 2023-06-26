package com.seiko.greenqrscanner.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import dev.icerock.moko.resources.StringResource

@Composable
@ReadOnlyComposable
expect fun stringResource(resource: StringResource): String
