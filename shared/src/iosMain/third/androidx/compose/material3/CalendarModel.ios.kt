package androidx.compose.material3

@ExperimentalMaterial3Api
internal actual fun createCalendarModel(locale: CalendarLocale): CalendarModel {
    return KotlinxDateTimeCalendarModelImpl(locale)
}

@ExperimentalMaterial3Api
actual fun formatWithSkeleton(
    utcTimeMillis: Long,
    skeleton: String,
    locale: CalendarLocale
): String {
    TODO()
}
