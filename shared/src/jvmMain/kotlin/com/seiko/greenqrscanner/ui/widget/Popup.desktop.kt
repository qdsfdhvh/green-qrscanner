package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
actual fun Popup(
    alignment: Alignment,
    onDismissRequest: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    androidx.compose.ui.window.Popup(
        alignment = alignment,
        onDismissRequest = onDismissRequest,
        content = content,
    )
}
