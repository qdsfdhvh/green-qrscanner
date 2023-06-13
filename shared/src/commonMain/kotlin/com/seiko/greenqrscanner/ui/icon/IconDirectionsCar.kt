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
fun rememberDirectionsCar(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "directions_car",
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
                moveTo(6f, 19f)
                verticalLineToRelative(0.525f)
                quadToRelative(0f, 0.625f, -0.425f, 1.05f)
                quadTo(5.15f, 21f, 4.5f, 21f)
                quadToRelative(-0.625f, 0f, -1.062f, -0.438f)
                quadTo(3f, 20.125f, 3f, 19.5f)
                verticalLineTo(12f)
                lineToRelative(2.1f, -6f)
                quadToRelative(0.15f, -0.45f, 0.538f, -0.725f)
                quadTo(6.025f, 5f, 6.5f, 5f)
                horizontalLineToRelative(11f)
                quadToRelative(0.475f, 0f, 0.863f, 0.275f)
                quadToRelative(0.387f, 0.275f, 0.537f, 0.725f)
                lineToRelative(2.1f, 6f)
                verticalLineToRelative(7.525f)
                quadToRelative(0f, 0.625f, -0.425f, 1.05f)
                quadTo(20.15f, 21f, 19.5f, 21f)
                quadToRelative(-0.625f, 0f, -1.062f, -0.438f)
                quadTo(18f, 20.125f, 18f, 19.5f)
                verticalLineTo(19f)
                close()
                moveToRelative(-0.2f, -9f)
                horizontalLineToRelative(12.4f)
                lineToRelative(-1.05f, -3f)
                horizontalLineTo(6.85f)
                close()
                moveTo(5f, 12f)
                verticalLineToRelative(5f)
                close()
                moveToRelative(2.5f, 4f)
                quadToRelative(0.625f, 0f, 1.062f, -0.438f)
                quadTo(9f, 15.125f, 9f, 14.5f)
                reflectiveQuadToRelative(-0.438f, -1.062f)
                quadTo(8.125f, 13f, 7.5f, 13f)
                reflectiveQuadToRelative(-1.062f, 0.438f)
                quadTo(6f, 13.875f, 6f, 14.5f)
                reflectiveQuadToRelative(0.438f, 1.062f)
                quadTo(6.875f, 16f, 7.5f, 16f)
                close()
                moveToRelative(9f, 0f)
                quadToRelative(0.625f, 0f, 1.062f, -0.438f)
                quadTo(18f, 15.125f, 18f, 14.5f)
                reflectiveQuadToRelative(-0.438f, -1.062f)
                quadTo(17.125f, 13f, 16.5f, 13f)
                reflectiveQuadToRelative(-1.062f, 0.438f)
                quadTo(15f, 13.875f, 15f, 14.5f)
                reflectiveQuadToRelative(0.438f, 1.062f)
                quadTo(15.875f, 16f, 16.5f, 16f)
                close()
                moveTo(5f, 17f)
                horizontalLineToRelative(14f)
                verticalLineToRelative(-5f)
                horizontalLineTo(5f)
                close()
            }
        }.build()
    }
}
