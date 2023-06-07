package com.seiko.greenqrscanner.ui.widget

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import compose.icons.TablerIcons
import compose.icons.tablericons.ChevronLeft

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(TablerIcons.ChevronLeft, "back")
    }
}
