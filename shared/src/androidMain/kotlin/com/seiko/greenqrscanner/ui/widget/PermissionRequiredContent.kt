package com.seiko.greenqrscanner.ui.widget

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.seiko.greenqrscanner.option.PermissionPlace

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequiredContent(
    permissionPlace: PermissionPlace,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val permissionsState = rememberMultiplePermissionsState(permissionPlace.permissions)
    Box(modifier) {
        when {
            permissionsState.allPermissionsGranted -> {
                content()
            }
            else -> {
                val context = LocalContext.current
                PermissionDeniedContent(
                    permissionPlace = permissionPlace,
                    shouldShowRationale = permissionsState.shouldShowRationale,
                    onRequest = {
                        if (permissionsState.shouldShowRationale) {
                            context.startActivity(
                                Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", context.packageName, null),
                                ),
                            )
                        } else {
                            permissionsState.launchMultiplePermissionRequest()
                        }
                    },
                    modifier = Modifier.matchParentSize(),
                )
            }
        }
    }
}

@Composable
private fun PermissionDeniedContent(
    permissionPlace: PermissionPlace,
    shouldShowRationale: Boolean,
    onRequest: () -> Unit,
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
        Button(onClick = onRequest) {
            Text(
                if (shouldShowRationale) {
                    "Go to Settings"
                } else {
                    "OK"
                },
            )
        }
    }
}
