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
fun rememberMyLocation(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "my_location",
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
                moveTo(12f, 22.95f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.287f)
                quadTo(11f, 22.375f, 11f, 21.95f)
                verticalLineToRelative(-1f)
                quadToRelative(-3.125f, -0.35f, -5.362f, -2.587f)
                quadTo(3.4f, 16.125f, 3.05f, 13f)
                horizontalLineToRelative(-1f)
                quadToRelative(-0.425f, 0f, -0.713f, -0.288f)
                quadToRelative(-0.287f, -0.287f, -0.287f, -0.712f)
                reflectiveQuadToRelative(0.287f, -0.713f)
                quadTo(1.625f, 11f, 2.05f, 11f)
                horizontalLineToRelative(1f)
                quadToRelative(0.35f, -3.125f, 2.588f, -5.363f)
                quadTo(7.875f, 3.4f, 11f, 3.05f)
                verticalLineToRelative(-1f)
                quadToRelative(0f, -0.425f, 0.288f, -0.713f)
                quadToRelative(0.287f, -0.287f, 0.712f, -0.287f)
                reflectiveQuadToRelative(0.713f, 0.287f)
                quadToRelative(0.287f, 0.288f, 0.287f, 0.713f)
                verticalLineToRelative(1f)
                quadToRelative(3.125f, 0.35f, 5.363f, 2.587f)
                quadTo(20.6f, 7.875f, 20.95f, 11f)
                horizontalLineToRelative(1f)
                quadToRelative(0.425f, 0f, 0.713f, 0.287f)
                quadToRelative(0.287f, 0.288f, 0.287f, 0.713f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadToRelative(-0.288f, 0.288f, -0.713f, 0.288f)
                horizontalLineToRelative(-1f)
                quadToRelative(-0.35f, 3.125f, -2.587f, 5.363f)
                quadTo(16.125f, 20.6f, 13f, 20.95f)
                verticalLineToRelative(1f)
                quadToRelative(0f, 0.425f, -0.287f, 0.713f)
                quadToRelative(-0.288f, 0.287f, -0.713f, 0.287f)
                close()
                moveTo(12f, 19f)
                quadToRelative(2.9f, 0f, 4.95f, -2.05f)
                quadTo(19f, 14.9f, 19f, 12f)
                quadToRelative(0f, -2.9f, -2.05f, -4.95f)
                quadTo(14.9f, 5f, 12f, 5f)
                quadTo(9.1f, 5f, 7.05f, 7.05f)
                quadTo(5f, 9.1f, 5f, 12f)
                quadToRelative(0f, 2.9f, 2.05f, 4.95f)
                quadTo(9.1f, 19f, 12f, 19f)
                close()
                moveToRelative(0f, -3f)
                quadToRelative(-1.65f, 0f, -2.825f, -1.175f)
                quadTo(8f, 13.65f, 8f, 12f)
                quadToRelative(0f, -1.65f, 1.175f, -2.825f)
                quadTo(10.35f, 8f, 12f, 8f)
                quadToRelative(1.65f, 0f, 2.825f, 1.175f)
                quadTo(16f, 10.35f, 16f, 12f)
                quadToRelative(0f, 1.65f, -1.175f, 2.825f)
                quadTo(13.65f, 16f, 12f, 16f)
                close()
                moveToRelative(0f, -2f)
                quadToRelative(0.825f, 0f, 1.413f, -0.588f)
                quadTo(14f, 12.825f, 14f, 12f)
                reflectiveQuadToRelative(-0.587f, -1.413f)
                quadTo(12.825f, 10f, 12f, 10f)
                quadToRelative(-0.825f, 0f, -1.412f, 0.587f)
                quadTo(10f, 11.175f, 10f, 12f)
                quadToRelative(0f, 0.825f, 0.588f, 1.412f)
                quadTo(11.175f, 14f, 12f, 14f)
                close()
                moveToRelative(0f, -2f)
                close()
            }
        }.build()
    }
}
