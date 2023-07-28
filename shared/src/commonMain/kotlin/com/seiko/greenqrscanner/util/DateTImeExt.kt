package com.seiko.greenqrscanner.util

import kotlinx.datetime.LocalTime

fun LocalTime.copy(
    hour: Int = this.hour,
    minute: Int = this.minute,
    second: Int = this.second,
    nanosecond: Int = this.nanosecond,
): LocalTime {
    return LocalTime(
        hour = hour,
        minute = minute,
        second = second,
        nanosecond = nanosecond,
    )
}
