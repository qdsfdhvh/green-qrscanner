/*
 *  Mask-Android
 *
 *  Copyright (C) 2022 DimensionDev and Contributors
 *
 *  This file is part of Mask X.
 *
 *  Mask X is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Mask-Android is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with Mask-Android.  If not, see <http://www.gnu.org/licenses/>.
 */
package androidx.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
@kotlin.jvm.JvmInline
value class Strings2 private constructor(@Suppress("unused") private val value: Int) {
    companion object {
        val NavigationMenu = Strings2(0)
        val CloseDrawer = Strings2(1)
        val CloseSheet = Strings2(2)
        val DefaultErrorMessage = Strings2(3)
        val ExposedDropdownMenu = Strings2(4)
        val SliderRangeStart = Strings2(5)
        val SliderRangeEnd = Strings2(6)
        val DatePickerTitle = Strings2(12)
        val DatePickerHeadline = Strings2(13)
        val DatePickerYearPickerPaneTitle = Strings2(14)
        val DatePickerSwitchToYearSelection = Strings2(15)
        val DatePickerSwitchToDaySelection = Strings2(16)
        val DatePickerSwitchToNextMonth = Strings2(17)
        val DatePickerSwitchToPreviousMonth = Strings2(18)
        val DatePickerNavigateToYearDescription = Strings2(19)
        val DatePickerHeadlineDescription = Strings2(20)
        val DatePickerNoSelectionDescription = Strings2(21)
        val DatePickerTodayDescription = Strings2(22)
        val DatePickerScrollToShowLaterYears = Strings2(23)
        val DatePickerScrollToShowEarlierYears = Strings2(24)
        val DateInputTitle = Strings2(25)
        val DateInputHeadline = Strings2(26)
        val DateInputLabel = Strings2(27)
        val DateInputHeadlineDescription = Strings2(28)
        val DateInputNoInputDescription = Strings2(29)
        val DateInputInvalidNotAllowed = Strings2(30)
        val DateInputInvalidForPattern = Strings2(31)
        val DateInputInvalidYearRange = Strings2(32)
        val DatePickerSwitchToCalendarMode = Strings2(33)
        val DatePickerSwitchToInputMode = Strings2(34)
        val DateRangePickerTitle = Strings2(35)
        val DateRangePickerStartHeadline = Strings2(36)
        val DateRangePickerEndHeadline = Strings2(37)
        val DateRangePickerScrollToShowNextMonth = Strings2(38)
        val DateRangePickerScrollToShowPreviousMonth = Strings2(39)
        val DateRangePickerDayInRange = Strings2(40)
        val DateRangeInputTitle = Strings2(41)
        val DateRangeInputInvalidRangeInput = Strings2(42)
        val BottomSheetPaneTitle = Strings2(43)
        val BottomSheetDragHandleDescription = Strings2(44)
        val BottomSheetPartialExpandDescription = Strings2(45)
        val BottomSheetDismissDescription = Strings2(46)
        val BottomSheetExpandDescription = Strings2(47)
    }
}

@Composable
fun getString2(string: Strings2): String {
    return when (string) {
        Strings2.NavigationMenu -> "Navigation menu"
        Strings2.CloseDrawer -> "Close drawer"
        Strings2.CloseSheet -> "Close sheet"
        Strings2.DefaultErrorMessage -> "Error"
        Strings2.ExposedDropdownMenu -> "Exposed dropdown menu"
        Strings2.SliderRangeStart -> "Slider range start"
        Strings2.SliderRangeEnd -> "Slider range end"
        Strings2.DatePickerTitle -> "Select date"
        Strings2.DatePickerHeadline -> "Selected date"
        Strings2.DatePickerYearPickerPaneTitle -> "Year picker visible"
        Strings2.DatePickerSwitchToYearSelection -> "Switch to selecting a year"
        Strings2.DatePickerSwitchToDaySelection ->
            "Swipe to select a year, or tap to switch " +
                "back to selecting a day"
        Strings2.DatePickerSwitchToNextMonth -> "Change to next month"
        Strings2.DatePickerSwitchToPreviousMonth -> "Change to previous month"
        Strings2.DatePickerNavigateToYearDescription -> "Navigate to year %s"
        Strings2.DatePickerHeadlineDescription -> "Current selection: %s"
        Strings2.DatePickerNoSelectionDescription -> "None"
        Strings2.DatePickerTodayDescription -> "Today"
        Strings2.DatePickerScrollToShowLaterYears -> "Scroll to show later years"
        Strings2.DatePickerScrollToShowEarlierYears -> "Scroll to show earlier years"
        Strings2.DateInputTitle -> "Select date"
        Strings2.DateInputHeadline -> "Entered date"
        Strings2.DateInputLabel -> "Date"
        Strings2.DateInputHeadlineDescription -> "Entered date: %s"
        Strings2.DateInputNoInputDescription -> "None"
        Strings2.DateInputInvalidNotAllowed -> "Date not allowed: %s"
        Strings2.DateInputInvalidForPattern -> "Date does not match expected pattern: %s"
        Strings2.DateInputInvalidYearRange -> "Date out of expected year range %s - %s"
        Strings2.DatePickerSwitchToCalendarMode -> "Switch to calendar input mode"
        Strings2.DatePickerSwitchToInputMode -> "Switch to text input mode"
        Strings2.DateRangePickerTitle -> "Select dates"
        Strings2.DateRangePickerStartHeadline -> "Start date"
        Strings2.DateRangePickerEndHeadline -> "End date"
        Strings2.DateRangePickerScrollToShowNextMonth -> "Scroll to show the next month"
        Strings2.DateRangePickerScrollToShowPreviousMonth -> "Scroll to show the previous month"
        Strings2.DateRangePickerDayInRange -> "In range"
        Strings2.DateRangeInputTitle -> "Enter dates"
        Strings2.DateRangeInputInvalidRangeInput -> "Invalid date range input"
        Strings2.BottomSheetPaneTitle -> "Bottom Sheet"
        Strings2.BottomSheetDragHandleDescription -> "Drag Handle"
        Strings2.BottomSheetPartialExpandDescription -> "Collapse bottom sheet"
        Strings2.BottomSheetDismissDescription -> "Dismiss bottom sheet"
        Strings2.BottomSheetExpandDescription -> "Expand bottom sheet"
        else -> ""
    }
}
