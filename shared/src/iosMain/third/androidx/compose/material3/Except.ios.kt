package androidx.compose.material3

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.currentLocale

/**
 * Represents a Locale for the calendar. This locale will be used when formatting dates, determining
 * the input format, and more.
 */
actual typealias CalendarLocale = NSLocale

/**
 * Returns the default [CalendarLocale].
 */
internal actual fun defaultLocale(): CalendarLocale = NSLocale.currentLocale()

/**
 * Returns a string representation of an integer for the current Locale.
 */
internal actual fun Int.toLocalString(
    minDigits: Int,
    maxDigits: Int,
    isGroupingUsed: Boolean
): String {
    val formatter = NSNumberFormatter()
    formatter.minimumIntegerDigits = minDigits.toULong()
    formatter.maximumIntegerDigits = maxDigits.toULong()
    return formatter.stringFromNumber(NSNumber(this))!!
}
