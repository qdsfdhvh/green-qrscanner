package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun OverflowTextSelection(
    text: String,
    onFullContentClick: () -> Unit,
    modifier: Modifier = Modifier,
    maxLines: Int = 2,
) {
    Box(modifier) {
        var hasVisualOverflow by rememberSaveable { mutableStateOf(false) }
        if (hasVisualOverflow) {
            DisableSelection {
                Text(
                    text,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable(onClick = onFullContentClick),
                )
            }
        } else {
            SelectionContainer {
                Text(
                    text,
                    maxLines = maxLines,
                    onTextLayout = {
                        hasVisualOverflow = it.hasVisualOverflow
                    },
                )
            }
        }
    }
}
