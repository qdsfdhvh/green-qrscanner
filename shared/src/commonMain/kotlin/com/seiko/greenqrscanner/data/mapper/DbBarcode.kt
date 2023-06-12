package com.seiko.greenqrscanner.data.mapper

import app.com.seiko.greenqrscanner.DbBarcode
import com.seiko.greenqrscanner.data.model.UiBarcode
import kotlinx.datetime.Instant

fun DbBarcode.toUi() = UiBarcode(
    rawValue = raw_value,
    time = Instant.fromEpochMilliseconds(update_time).toString(),
)
