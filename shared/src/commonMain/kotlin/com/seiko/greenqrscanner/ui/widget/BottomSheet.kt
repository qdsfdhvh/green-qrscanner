package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BottomSheet(
    onDismissRequest: (() -> Unit),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val bottomSheetState = rememberStandardBottomSheetState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState,
    )
    BottomSheetScaffold(
        sheetContent = {
            content()
        },
        scaffoldState = bottomSheetScaffoldState,
        modifier = modifier,
    ) {
        Spacer(
            Modifier
                .clickable(onClick = onDismissRequest)
                .background(Color.Black.copy(0.4f))
                .fillMaxSize(),
        )
    }
}
