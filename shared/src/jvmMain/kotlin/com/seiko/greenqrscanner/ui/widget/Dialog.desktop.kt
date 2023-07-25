package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.rememberDialogState

@Composable
actual fun Dialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    content: @Composable () -> Unit,
) {
    androidx.compose.ui.window.Dialog(
        onCloseRequest = onDismissRequest,
        state = rememberDialogState(size = properties.size),
        title = properties.title,
        icon = properties.icon,
        resizable = properties.resizable,
    ) {
        content()
    }
}
