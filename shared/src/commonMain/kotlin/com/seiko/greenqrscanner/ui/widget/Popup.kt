package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.PopupProperties

@Composable
fun Popup(
    alignment: Alignment = Alignment.BottomCenter,
    offset: IntOffset = IntOffset(0, 0),
    onDismissRequest: (() -> Unit)? = null,
    properties: PopupProperties = PopupProperties(),
    content: @Composable () -> Unit
) {
    androidx.compose.ui.window.Popup(
        alignment = alignment,
        onDismissRequest = onDismissRequest,
        offset = offset,
        properties = properties,
        content = content,
    )
}
