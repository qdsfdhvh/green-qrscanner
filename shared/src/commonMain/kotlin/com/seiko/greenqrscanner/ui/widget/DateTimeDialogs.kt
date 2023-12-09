/*
 * Taken from https://github.com/chrisbanes/tivi/blob/main/common/ui/compose/src/commonMain/kotlin/app/tivi/common/compose/ui/DateTimeDialogs.kt
 */
package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.seiko.greenqrscanner.MR
import com.seiko.greenqrscanner.util.stringResource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
expect fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    selectedDate: LocalDate = remember {
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    },
    confirmLabel: String = stringResource(MR.strings.ok),
    minimumDate: LocalDate? = null,
    maximumDate: LocalDate? = null,
    title: String = "",
)

@Composable
expect fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    onTimeChanged: (LocalTime) -> Unit,
    selectedTime: LocalTime = remember {
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
    },
    confirmLabel: String = stringResource(MR.strings.ok),
    title: String = "",
    is24Hour: Boolean = false,
)
