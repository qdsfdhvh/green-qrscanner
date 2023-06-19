package com.seiko.greenqrscanner.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.LayoutDirection

operator fun PaddingValues.plus(
    paddingValues: PaddingValues,
): PaddingValues {
    return add(paddingValues, LayoutDirection.Ltr)
}

fun PaddingValues.add(
    paddingValues: PaddingValues,
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
) = PaddingValues(
    top = calculateTopPadding() + paddingValues.calculateTopPadding(),
    bottom = calculateBottomPadding() + paddingValues.calculateBottomPadding(),
    start = calculateLeftPadding(layoutDirection) +
        paddingValues.calculateLeftPadding(layoutDirection),
    end = calculateRightPadding(layoutDirection) +
        paddingValues.calculateRightPadding(layoutDirection),
)
