package androidx.compose.material3

/**
 * Returns a [CalendarModel] to be used by the date picker.
 */
@ExperimentalMaterial3Api
internal actual fun createCalendarModel(locale: CalendarLocale): CalendarModel =
    LegacyCalendarModelImpl(locale)

/**
 * Formats a UTC timestamp into a string with a given date format skeleton.
 *
 * @param utcTimeMillis a UTC timestamp to format (milliseconds from epoch)
 * @param skeleton a date format skeleton
 * @param locale the [CalendarLocale] to use when formatting the given timestamp
 */
@ExperimentalMaterial3Api
actual fun formatWithSkeleton(
    utcTimeMillis: Long,
    skeleton: String,
    locale: CalendarLocale
): String {
    // Note: there is no equivalent in Java for Android's DateFormat.getBestDateTimePattern.
    // The JDK SimpleDateFormat expects a pattern, so the results will be "2023Jan7",
    // "2023January", etc. in case a skeleton holds an actual ICU skeleton and not a pattern.
    return LegacyCalendarModelImpl.formatWithPattern(
        utcTimeMillis = utcTimeMillis,
        pattern = skeleton,
        locale = locale,
    )
}
