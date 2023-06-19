@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package androidx.compose.material3.tokens

import androidx.compose.ui.unit.dp

internal object SheetBottomTokens {
    val DockedContainerColor = ColorSchemeKeyTokens.Surface
    val DockedContainerShape = ShapeKeyTokens.CornerExtraLargeTop
    val DockedContainerSurfaceTintLayerColor = ColorSchemeKeyTokens.SurfaceTint
    val DockedDragHandleColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val DockedDragHandleHeight = 4.0.dp
    const val DockedDragHandleOpacity = 0.4f
    val DockedDragHandleWidth = 32.0.dp
    val DockedMinimizedContainerShape = ShapeKeyTokens.CornerNone
    val DockedModalContainerElevation = ElevationTokens.Level1
    val DockedStandardContainerElevation = ElevationTokens.Level1
}
