package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.option.PermissionPlace

@Composable
fun PermissionDeniedContent(
    permissionPlace: PermissionPlace,
    requestButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(permissionPlace.title)
        Spacer(Modifier.height(8.dp))
        Text(permissionPlace.desc)
        Spacer(Modifier.height(16.dp))
        requestButton()
    }
}
