package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
expect fun Popup(
    alignment: Alignment = Alignment.BottomCenter,
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit
)
