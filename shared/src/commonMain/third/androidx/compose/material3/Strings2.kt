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
        Strings2.BottomSheetPaneTitle -> "Bottom Sheet"
        Strings2.BottomSheetDragHandleDescription -> "Drag Handle"
        Strings2.BottomSheetPartialExpandDescription -> "Collapse bottom sheet"
        Strings2.BottomSheetDismissDescription -> "Dismiss bottom sheet"
        Strings2.BottomSheetExpandDescription -> "Expand bottom sheet"
        else -> ""
    }
}
