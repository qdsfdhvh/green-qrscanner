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
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequiredContent(
    feature: String,
    permission: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val permissionsState = rememberPermissionState(permission)
    Box(modifier) {
        when (val status = permissionsState.status) {
            PermissionStatus.Granted -> {
                content()
            }
            is PermissionStatus.Denied -> {
                val context = LocalContext.current
                PermissionDeniedContent(
                    feature = feature,
                    permission = permission,
                    onRequest = {
                        if (!status.shouldShowRationale) {
                            permissionsState.launchPermissionRequest()
                        } else {
                            context.startActivity(
                                Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", context.packageName, null),
                                ),
                            )
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
    feature: String,
    permission: String,
    onRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Allow ${permission.permissionName} Access")
        Spacer(Modifier.height(8.dp))
        Text("We need your permission to $feature")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onRequest) {
            Text("OK")
        }
    }
}

private val String.permissionName: String
    get() = when (this) {
        android.Manifest.permission.CAMERA -> "Camera"
        // add other used permission
        else -> this
    }

