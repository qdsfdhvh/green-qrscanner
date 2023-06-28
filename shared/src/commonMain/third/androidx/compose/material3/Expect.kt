package androidx.compose.material3

/**
 * Represents a Locale for the calendar. This locale will be used when formatting dates, determining
 * the input format, and more.
 *
 * Note: For JVM based platforms, this would be equivalent to [java.util.Locale].
 */
@ExperimentalMaterial3Api
expect class CalendarLocale

/**
 * Returns the default [CalendarLocale].
 *
 * Note: For JVM based platforms, this would be equivalent to [java.util.Locale.getDefault].
 */
@OptIn(ExperimentalMaterial3Api::class)
internal expect fun defaultLocale(): CalendarLocale

/**
 * Returns a string representation of an integer for the current Locale.
 *
 * @param minDigits sets the minimum number of digits allowed in the integer portion of a number.
 * If the minDigits value is greater than the [maxDigits] value, then [maxDigits] will also be set
 * to this value.
 * @param maxDigits sets the maximum number of digits allowed in the integer portion of a number.
 * If this maxDigits value is less than the [minDigits] value, then [minDigits] will also be set to
 * this value.
 * @param isGroupingUsed set whether or not grouping will be used when formatting into a local
 * string. By default, this value is false, which eliminates any use of delimiters when formatting
 * the integer.
 */
internal expect fun Int.toLocalString(
    minDigits: Int = 1,
    maxDigits: Int = 40,
    isGroupingUsed: Boolean = false
): String
