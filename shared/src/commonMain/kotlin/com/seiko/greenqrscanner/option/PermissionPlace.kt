package com.seiko.greenqrscanner.option

enum class PermissionPlace(
    val title: String,
    val desc: String,
) {
    QrScan(
        title = "Allow Camera Access",
        desc = "We need your permission to QR Scan",
    )
}
