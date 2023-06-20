package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            Icons.Rounded.ArrowBackIosNew,
            contentDescription = "back",
            modifier = Modifier.size(24.dp),
        )
    }
}
