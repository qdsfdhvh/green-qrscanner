package com.seiko.greenqrscanner.ui.widget

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.seiko.greenqrscanner.ui.icon.rememberArrowBackIosNew

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(rememberArrowBackIosNew(), "back")
    }
}
