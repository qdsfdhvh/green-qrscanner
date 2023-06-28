package androidx.compose.material3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateRangeInputContent(
    selectedStartDateMillis: Long?,
    selectedEndDateMillis: Long?,
    onDatesSelectionChange: (startDateMillis: Long?, endDateMillis: Long?) -> Unit,
    calendarModel: CalendarModel,
    yearRange: IntRange,
    dateFormatter: DatePickerFormatter,
    selectableDates: SelectableDates,
    colors: DatePickerColors
) {
    // Obtain the DateInputFormat for the default Locale.
    val defaultLocale = defaultLocale()
    val dateInputFormat = remember(defaultLocale) {
        calendarModel.getDateInputFormat(defaultLocale)
    }
    val errorDatePattern = getString2(Strings2.DateInputInvalidForPattern)
    val errorDateOutOfYearRange = getString2(Strings2.DateInputInvalidYearRange)
    val errorInvalidNotAllowed = getString2(Strings2.DateInputInvalidNotAllowed)
    val errorInvalidRange = getString2(Strings2.DateRangeInputInvalidRangeInput)
    val dateInputValidator = remember(dateInputFormat, dateFormatter) {
        DateInputValidator(
            yearRange = yearRange,
            selectableDates = selectableDates,
            dateInputFormat = dateInputFormat,
            dateFormatter = dateFormatter,
            errorDatePattern = errorDatePattern,
            errorDateOutOfYearRange = errorDateOutOfYearRange,
            errorInvalidNotAllowed = errorInvalidNotAllowed,
            errorInvalidRangeInput = errorInvalidRange,
        )
    }
    // Apply both start and end dates for proper validation.
    dateInputValidator.apply {
        currentStartDateMillis = selectedStartDateMillis
        currentEndDateMillis = selectedEndDateMillis
    }
    Row(
        modifier = Modifier.padding(paddingValues = InputTextFieldPadding),
        horizontalArrangement = Arrangement.spacedBy(TextFieldSpacing),
    ) {
        val pattern = dateInputFormat.patternWithDelimiters.uppercase()
        val startRangeText = getString2(string = Strings2.DateRangePickerStartHeadline)
        DateInputTextField(
            modifier = Modifier.weight(0.5f),
            calendarModel = calendarModel,
            label = {
                Text(
                    startRangeText,
                    modifier = Modifier.semantics {
                        contentDescription = "$startRangeText, $pattern"
                    },
                )
            },
            placeholder = { Text(pattern, modifier = Modifier.clearAndSetSemantics { }) },
            initialDateMillis = selectedStartDateMillis,
            onDateSelectionChange = { startDateMillis ->
                // Delegate to the onDatesSelectionChange and change just the start date.
                onDatesSelectionChange(startDateMillis, selectedEndDateMillis)
            },
            inputIdentifier = InputIdentifier.StartDateInput,
            dateInputValidator = dateInputValidator,
            dateInputFormat = dateInputFormat,
            locale = defaultLocale,
            colors = colors,
        )
        val endRangeText = getString2(string = Strings2.DateRangePickerEndHeadline)
        DateInputTextField(
            modifier = Modifier.weight(0.5f),
            calendarModel = calendarModel,
            label = {
                Text(
                    endRangeText,
                    modifier = Modifier.semantics {
                        contentDescription = "$endRangeText, $pattern"
                    },
                )
            },
            placeholder = { Text(pattern, modifier = Modifier.clearAndSetSemantics { }) },
            initialDateMillis = selectedEndDateMillis,
            onDateSelectionChange = { endDateMillis ->
                // Delegate to the onDatesSelectionChange and change just the end date.
                onDatesSelectionChange(selectedStartDateMillis, endDateMillis)
            },
            inputIdentifier = InputIdentifier.EndDateInput,
            dateInputValidator = dateInputValidator,
            dateInputFormat = dateInputFormat,
            locale = defaultLocale,
            colors = colors,
        )
    }
}

private val TextFieldSpacing = 8.dp
