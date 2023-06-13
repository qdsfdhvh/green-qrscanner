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
fun rememberCalendarMonth(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "calendar_month",
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
                moveTo(12f, 14f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(11f, 13.425f, 11f, 13f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(11.575f, 12f, 12f, 12f)
                reflectiveQuadToRelative(0.713f, 0.287f)
                quadTo(13f, 12.575f, 13f, 13f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(12.425f, 14f, 12f, 14f)
                close()
                moveToRelative(-4f, 0f)
                quadToRelative(-0.425f, 0f, -0.713f, -0.288f)
                quadTo(7f, 13.425f, 7f, 13f)
                reflectiveQuadToRelative(0.287f, -0.713f)
                quadTo(7.575f, 12f, 8f, 12f)
                reflectiveQuadToRelative(0.713f, 0.287f)
                quadTo(9f, 12.575f, 9f, 13f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(8.425f, 14f, 8f, 14f)
                close()
                moveToRelative(8f, 0f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(15f, 13.425f, 15f, 13f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(15.575f, 12f, 16f, 12f)
                reflectiveQuadToRelative(0.712f, 0.287f)
                quadTo(17f, 12.575f, 17f, 13f)
                reflectiveQuadToRelative(-0.288f, 0.712f)
                quadTo(16.425f, 14f, 16f, 14f)
                close()
                moveToRelative(-4f, 4f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(11f, 17.425f, 11f, 17f)
                reflectiveQuadToRelative(0.288f, -0.712f)
                quadTo(11.575f, 16f, 12f, 16f)
                reflectiveQuadToRelative(0.713f, 0.288f)
                quadTo(13f, 16.575f, 13f, 17f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(12.425f, 18f, 12f, 18f)
                close()
                moveToRelative(-4f, 0f)
                quadToRelative(-0.425f, 0f, -0.713f, -0.288f)
                quadTo(7f, 17.425f, 7f, 17f)
                reflectiveQuadToRelative(0.287f, -0.712f)
                quadTo(7.575f, 16f, 8f, 16f)
                reflectiveQuadToRelative(0.713f, 0.288f)
                quadTo(9f, 16.575f, 9f, 17f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(8.425f, 18f, 8f, 18f)
                close()
                moveToRelative(8f, 0f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(15f, 17.425f, 15f, 17f)
                reflectiveQuadToRelative(0.288f, -0.712f)
                quadTo(15.575f, 16f, 16f, 16f)
                reflectiveQuadToRelative(0.712f, 0.288f)
                quadTo(17f, 16.575f, 17f, 17f)
                reflectiveQuadToRelative(-0.288f, 0.712f)
                quadTo(16.425f, 18f, 16f, 18f)
                close()
                moveTo(5f, 22f)
                quadToRelative(-0.825f, 0f, -1.413f, -0.587f)
                quadTo(3f, 20.825f, 3f, 20f)
                verticalLineTo(6f)
                quadToRelative(0f, -0.825f, 0.587f, -1.412f)
                quadTo(4.175f, 4f, 5f, 4f)
                horizontalLineToRelative(1f)
                verticalLineTo(3f)
                quadToRelative(0f, -0.425f, 0.287f, -0.713f)
                quadTo(6.575f, 2f, 7f, 2f)
                reflectiveQuadToRelative(0.713f, 0.287f)
                quadTo(8f, 2.575f, 8f, 3f)
                verticalLineToRelative(1f)
                horizontalLineToRelative(8f)
                verticalLineTo(3f)
                quadToRelative(0f, -0.425f, 0.288f, -0.713f)
                quadTo(16.575f, 2f, 17f, 2f)
                reflectiveQuadToRelative(0.712f, 0.287f)
                quadTo(18f, 2.575f, 18f, 3f)
                verticalLineToRelative(1f)
                horizontalLineToRelative(1f)
                quadToRelative(0.825f, 0f, 1.413f, 0.588f)
                quadTo(21f, 5.175f, 21f, 6f)
                verticalLineToRelative(14f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(19.825f, 22f, 19f, 22f)
                close()
                moveToRelative(0f, -2f)
                horizontalLineToRelative(14f)
                verticalLineTo(10f)
                horizontalLineTo(5f)
                verticalLineToRelative(10f)
                close()
                moveTo(5f, 8f)
                horizontalLineToRelative(14f)
                verticalLineTo(6f)
                horizontalLineTo(5f)
                close()
                moveToRelative(0f, 0f)
                verticalLineTo(6f)
                verticalLineToRelative(2f)
                close()
            }
        }.build()
    }
}
