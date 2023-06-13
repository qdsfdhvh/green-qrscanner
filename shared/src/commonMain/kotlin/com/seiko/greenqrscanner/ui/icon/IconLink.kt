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
fun rememberLink(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "link",
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
                moveTo(7f, 17f)
                quadToRelative(-2.075f, 0f, -3.537f, -1.463f)
                quadTo(2f, 14.075f, 2f, 12f)
                reflectiveQuadToRelative(1.463f, -3.538f)
                quadTo(4.925f, 7f, 7f, 7f)
                horizontalLineToRelative(3f)
                quadToRelative(0.425f, 0f, 0.713f, 0.287f)
                quadTo(11f, 7.575f, 11f, 8f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(10.425f, 9f, 10f, 9f)
                horizontalLineTo(7f)
                quadToRelative(-1.25f, 0f, -2.125f, 0.875f)
                reflectiveQuadTo(4f, 12f)
                quadToRelative(0f, 1.25f, 0.875f, 2.125f)
                reflectiveQuadTo(7f, 15f)
                horizontalLineToRelative(3f)
                quadToRelative(0.425f, 0f, 0.713f, 0.287f)
                quadToRelative(0.287f, 0.288f, 0.287f, 0.713f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(10.425f, 17f, 10f, 17f)
                close()
                moveToRelative(2f, -4f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(8f, 12.425f, 8f, 12f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(8.575f, 11f, 9f, 11f)
                horizontalLineToRelative(6f)
                quadToRelative(0.425f, 0f, 0.713f, 0.287f)
                quadToRelative(0.287f, 0.288f, 0.287f, 0.713f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(15.425f, 13f, 15f, 13f)
                close()
                moveToRelative(5f, 4f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(13f, 16.425f, 13f, 16f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(13.575f, 15f, 14f, 15f)
                horizontalLineToRelative(3f)
                quadToRelative(1.25f, 0f, 2.125f, -0.875f)
                reflectiveQuadTo(20f, 12f)
                quadToRelative(0f, -1.25f, -0.875f, -2.125f)
                reflectiveQuadTo(17f, 9f)
                horizontalLineToRelative(-3f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(13f, 8.425f, 13f, 8f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(13.575f, 7f, 14f, 7f)
                horizontalLineToRelative(3f)
                quadToRelative(2.075f, 0f, 3.538f, 1.462f)
                quadTo(22f, 9.925f, 22f, 12f)
                quadToRelative(0f, 2.075f, -1.462f, 3.537f)
                quadTo(19.075f, 17f, 17f, 17f)
                close()
            }
        }.build()
    }
}
