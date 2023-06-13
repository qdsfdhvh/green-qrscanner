package com.seiko.greenqrscanner.data.mapper

import app.com.seiko.greenqrscanner.DbBarcode
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.model.title

fun DbBarcode.toUi() = UiBarcode(
    rawValue = raw_value,
    // time = Instant.fromEpochMilliseconds(update_time).toString(),
    type = type,
    title = type.title,
)
