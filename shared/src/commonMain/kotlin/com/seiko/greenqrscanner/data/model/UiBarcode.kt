package com.seiko.greenqrscanner.data.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.Instant

@Immutable
data class UiBarcode(
    val rawValue: String,
    val type: BarcodeType,
    val time: Instant,
    val title: String,
    val isStar: Boolean,
)
