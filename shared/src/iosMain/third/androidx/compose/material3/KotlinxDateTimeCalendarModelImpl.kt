package androidx.compose.material3

@OptIn(ExperimentalMaterial3Api::class)
internal class KotlinxDateTimeCalendarModelImpl(locale: CalendarLocale) : CalendarModel(locale) {
    override val today: CalendarDate
        get() = TODO("Not yet implemented")
    override val firstDayOfWeek: Int
        get() = TODO("Not yet implemented")
    override val weekdayNames: List<Pair<String, String>>
        get() = TODO("Not yet implemented")

    override fun getCanonicalDate(timeInMillis: Long): CalendarDate {
        TODO("Not yet implemented")
    }

    override fun getMonth(timeInMillis: Long): CalendarMonth {
        TODO("Not yet implemented")
    }

    override fun getMonth(date: CalendarDate): CalendarMonth {
        TODO("Not yet implemented")
    }

    override fun getMonth(year: Int, month: Int): CalendarMonth {
        TODO("Not yet implemented")
    }

    override fun getDayOfWeek(date: CalendarDate): Int {
        TODO("Not yet implemented")
    }

    override fun plusMonths(from: CalendarMonth, addedMonthsCount: Int): CalendarMonth {
        TODO("Not yet implemented")
    }

    override fun minusMonths(from: CalendarMonth, subtractedMonthsCount: Int): CalendarMonth {
        TODO("Not yet implemented")
    }

    override fun parse(date: String, pattern: String): CalendarDate? {
        TODO("Not yet implemented")
    }

    override fun formatWithPattern(
        utcTimeMillis: Long,
        pattern: String,
        locale: CalendarLocale
    ): String {
        TODO("Not yet implemented")
    }

    override fun getDateInputFormat(locale: CalendarLocale): DateInputFormat {
        TODO("Not yet implemented")
    }
}
