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
fun rememberShoppingBag(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "shopping_bag",
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
                moveTo(6f, 22f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                quadTo(4f, 20.825f, 4f, 20f)
                verticalLineTo(8f)
                quadToRelative(0f, -0.825f, 0.588f, -1.412f)
                quadTo(5.175f, 6f, 6f, 6f)
                horizontalLineToRelative(2f)
                quadToRelative(0f, -1.65f, 1.175f, -2.825f)
                quadTo(10.35f, 2f, 12f, 2f)
                quadToRelative(1.65f, 0f, 2.825f, 1.175f)
                quadTo(16f, 4.35f, 16f, 6f)
                horizontalLineToRelative(2f)
                quadToRelative(0.825f, 0f, 1.413f, 0.588f)
                quadTo(20f, 7.175f, 20f, 8f)
                verticalLineToRelative(12f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(18.825f, 22f, 18f, 22f)
                close()
                moveToRelative(4f, -16f)
                horizontalLineToRelative(4f)
                quadToRelative(0f, -0.825f, -0.587f, -1.412f)
                quadTo(12.825f, 4f, 12f, 4f)
                quadToRelative(-0.825f, 0f, -1.412f, 0.588f)
                quadTo(10f, 5.175f, 10f, 6f)
                close()
                moveTo(6f, 20f)
                horizontalLineToRelative(12f)
                verticalLineTo(8f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(2f)
                quadToRelative(0f, 0.425f, -0.287f, 0.712f)
                quadTo(15.425f, 11f, 15f, 11f)
                reflectiveQuadToRelative(-0.712f, -0.288f)
                quadTo(14f, 10.425f, 14f, 10f)
                verticalLineTo(8f)
                horizontalLineToRelative(-4f)
                verticalLineToRelative(2f)
                quadToRelative(0f, 0.425f, -0.287f, 0.712f)
                quadTo(9.425f, 11f, 9f, 11f)
                reflectiveQuadToRelative(-0.712f, -0.288f)
                quadTo(8f, 10.425f, 8f, 10f)
                verticalLineTo(8f)
                horizontalLineTo(6f)
                verticalLineToRelative(12f)
                close()
                moveToRelative(0f, 0f)
                verticalLineTo(8f)
                verticalLineToRelative(12f)
                close()
            }
        }.build()
    }
}
