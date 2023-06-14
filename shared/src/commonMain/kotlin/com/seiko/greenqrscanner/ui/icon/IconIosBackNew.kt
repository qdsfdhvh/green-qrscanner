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
fun rememberArrowBackIosNew(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "arrow_back_ios_new",
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
                moveTo(15.125f, 21.1f)
                lineTo(6.7f, 12.7f)
                quadToRelative(-0.15f, -0.15f, -0.212f, -0.325f)
                quadToRelative(-0.063f, -0.175f, -0.063f, -0.375f)
                reflectiveQuadToRelative(0.063f, -0.375f)
                quadToRelative(0.062f, -0.175f, 0.212f, -0.325f)
                lineToRelative(8.425f, -8.425f)
                quadToRelative(0.35f, -0.35f, 0.875f, -0.35f)
                reflectiveQuadToRelative(0.9f, 0.375f)
                quadToRelative(0.375f, 0.375f, 0.375f, 0.875f)
                reflectiveQuadToRelative(-0.375f, 0.875f)
                lineTo(9.55f, 12f)
                lineToRelative(7.35f, 7.35f)
                quadToRelative(0.35f, 0.35f, 0.35f, 0.862f)
                quadToRelative(0f, 0.513f, -0.375f, 0.888f)
                reflectiveQuadToRelative(-0.875f, 0.375f)
                quadToRelative(-0.5f, 0f, -0.875f, -0.375f)
                close()
            }
        }.build()
    }
}
