package com.seiko.greenqrscanner.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class UiBarcode(
    val rawValue: String,
    val type: BarcodeType,
    // val time: String,
    // val icon: ImageVector,
    val title: String,
    val isStar: Boolean,
)
