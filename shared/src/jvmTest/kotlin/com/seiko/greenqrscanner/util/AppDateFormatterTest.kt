package com.seiko.greenqrscanner.util

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.util.Locale
import kotlin.test.Test
import kotlin.time.Duration.Companion.days

class AppDateFormatterTest {
    private val dateFormatter = AppDateFormatter(
        locale = Locale.UK,
        timeZone = TimeZone.UTC,
    )

    @Test
    fun formatShortDate() {
        assertThat(dateFormatter.formatShortDate(instant))
            .isEqualTo("01/06/2023")
    }

    @Test
    fun formatShortTime() {
        assertThat(dateFormatter.formatShortTime(localTime))
            .isEqualTo("01:23")
    }

    @Test
    fun formatMediumDate() {
        assertThat(dateFormatter.formatMediumDate(instant))
            .isEqualTo("1 Jun 2023")
    }

    @Test
    fun formatMediumDateTime() {
        assertThat(dateFormatter.formatMediumDateTime(instant))
            .isEqualTo("1 Jun 2023, 01:23:45")
    }

    @Test
    fun formatShortRelativeTime() {
        assertThat(dateFormatter.formatShortRelativeTime(instant, instant + 21.days))
            .isEqualTo("01/06/2023")

        assertThat(dateFormatter.formatShortRelativeTime(instant, instant + 2.days))
            .isEqualTo("01/06/2023")

        assertThat(dateFormatter.formatShortRelativeTime(instant, instant - 2.days))
            .isEqualTo("01/06/2023")

        assertThat(dateFormatter.formatShortRelativeTime(instant, instant - 21.days))
            .isEqualTo("01/06/2023")
    }

    companion object {
        private val localTime = LocalTime.parse("01:23:45")
        private val localDateTime = LocalDateTime.parse("2023-06-01T01:23:45")
        private val instant = localDateTime.toInstant(TimeZone.UTC)
    }
}
