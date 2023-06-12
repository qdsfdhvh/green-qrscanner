package com.seiko.greenqrscanner.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class UiBarcode(
    val rawValue: String,
    val time: String,
)
