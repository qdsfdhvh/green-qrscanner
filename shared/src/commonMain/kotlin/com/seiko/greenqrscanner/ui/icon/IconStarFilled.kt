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
fun rememberStarFilled(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "star",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0f,
            viewportHeight = 960.0f,
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
                moveTo(480.0f, 731.0f)
                lineTo(294.0f, 843.0f)
                quadToRelative(-8.0f, 5.0f, -17.0f, 4.5f)
                reflectiveQuadToRelative(-16.0f, -5.5f)
                quadToRelative(-7.0f, -5.0f, -10.5f, -13.0f)
                reflectiveQuadToRelative(-1.5f, -18.0f)
                lineToRelative(49.0f, -212.0f)
                lineToRelative(-164.0f, -143.0f)
                quadToRelative(-8.0f, -7.0f, -9.5f, -15.5f)
                reflectiveQuadToRelative(0.5f, -16.5f)
                quadToRelative(2.0f, -8.0f, 9.0f, -13.5f)
                reflectiveQuadToRelative(17.0f, -6.5f)
                lineToRelative(217.0f, -19.0f)
                lineToRelative(84.0f, -200.0f)
                quadToRelative(4.0f, -9.0f, 12.0f, -13.5f)
                reflectiveQuadToRelative(16.0f, -4.5f)
                quadToRelative(8.0f, 0.0f, 16.0f, 4.5f)
                reflectiveQuadToRelative(12.0f, 13.5f)
                lineToRelative(84.0f, 200.0f)
                lineToRelative(217.0f, 19.0f)
                quadToRelative(10.0f, 1.0f, 17.0f, 6.5f)
                reflectiveQuadToRelative(9.0f, 13.5f)
                quadToRelative(2.0f, 8.0f, 0.5f, 16.5f)
                reflectiveQuadTo(826.0f, 456.0f)
                lineTo(662.0f, 599.0f)
                lineToRelative(49.0f, 212.0f)
                quadToRelative(2.0f, 10.0f, -1.5f, 18.0f)
                reflectiveQuadTo(699.0f, 842.0f)
                quadToRelative(-7.0f, 5.0f, -16.0f, 5.5f)
                reflectiveQuadToRelative(-17.0f, -4.5f)
                lineTo(480.0f, 731.0f)
                close()
            }
        }.build()
    }
}
