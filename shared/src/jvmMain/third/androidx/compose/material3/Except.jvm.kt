package androidx.compose.material3

import java.text.NumberFormat

/**
 * Represents a Locale for the calendar. This locale will be used when formatting dates, determining
 * the input format, and more.
 */
actual typealias CalendarLocale = java.util.Locale

/**
 * Returns the default [CalendarLocale].
 */
internal actual fun defaultLocale(): CalendarLocale = java.util.Locale.getDefault()

/**
 * Returns a string representation of an integer for the current Locale.
 */
internal actual fun Int.toLocalString(
    minDigits: Int,
    maxDigits: Int,
    isGroupingUsed: Boolean
): String {
    val formatter = NumberFormat.getIntegerInstance()
    formatter.isGroupingUsed = isGroupingUsed
    formatter.minimumIntegerDigits = minDigits
    formatter.maximumIntegerDigits = maxDigits
    return formatter.format(this)
}
