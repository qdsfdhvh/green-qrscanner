package com.seiko.greenqrscanner.ui.icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberBarcode(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "barcode",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(1f, 5f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(14f)
                horizontalLineTo(1f)
                close()
                moveToRelative(6f, 0f)
                horizontalLineToRelative(1f)
                verticalLineToRelative(14f)
                horizontalLineTo(7f)
                close()
                moveTo(4f, 5f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(14f)
                horizontalLineTo(4f)
                close()
                moveToRelative(16f, 0f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(14f)
                horizontalLineToRelative(-3f)
                close()
                moveTo(10f, 5f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(14f)
                horizontalLineToRelative(-2f)
                close()
                moveToRelative(7f, 0f)
                horizontalLineToRelative(1f)
                verticalLineToRelative(14f)
                horizontalLineToRelative(-1f)
                close()
                moveToRelative(-4f, 0f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(14f)
                horizontalLineToRelative(-3f)
                close()
            }
        }.build()
    }
}
