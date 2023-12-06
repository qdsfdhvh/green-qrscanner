package com.seiko.greenqrscanner.data.mapper

import com.seiko.greenqrscanner.DbBarcode
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.model.title
import kotlinx.datetime.Instant

fun DbBarcode.toUi() = UiBarcode(
    rawValue = raw_value,
    time = Instant.fromEpochMilliseconds(update_time),
    type = type,
    title = type.title,
    isStar = is_star > 0,
)
