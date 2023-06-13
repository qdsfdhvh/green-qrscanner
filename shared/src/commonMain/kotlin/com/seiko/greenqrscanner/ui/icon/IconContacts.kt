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
fun rememberContacts(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "contacts",
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
                moveTo(5f, 23f)
                quadToRelative(-0.425f, 0f, -0.713f, -0.288f)
                quadTo(4f, 22.425f, 4f, 22f)
                reflectiveQuadToRelative(0.287f, -0.712f)
                quadTo(4.575f, 21f, 5f, 21f)
                horizontalLineToRelative(14f)
                quadToRelative(0.425f, 0f, 0.712f, 0.288f)
                quadToRelative(0.288f, 0.287f, 0.288f, 0.712f)
                reflectiveQuadToRelative(-0.288f, 0.712f)
                quadTo(19.425f, 23f, 19f, 23f)
                close()
                moveTo(5f, 3f)
                quadToRelative(-0.425f, 0f, -0.713f, -0.288f)
                quadTo(4f, 2.425f, 4f, 2f)
                reflectiveQuadToRelative(0.287f, -0.713f)
                quadTo(4.575f, 1f, 5f, 1f)
                horizontalLineToRelative(14f)
                quadToRelative(0.425f, 0f, 0.712f, 0.287f)
                quadTo(20f, 1.575f, 20f, 2f)
                reflectiveQuadToRelative(-0.288f, 0.712f)
                quadTo(19.425f, 3f, 19f, 3f)
                close()
                moveToRelative(7f, 10f)
                quadToRelative(1.25f, 0f, 2.125f, -0.875f)
                reflectiveQuadTo(15f, 10f)
                quadToRelative(0f, -1.25f, -0.875f, -2.125f)
                reflectiveQuadTo(12f, 7f)
                quadToRelative(-1.25f, 0f, -2.125f, 0.875f)
                reflectiveQuadTo(9f, 10f)
                quadToRelative(0f, 1.25f, 0.875f, 2.125f)
                reflectiveQuadTo(12f, 13f)
                close()
                moveToRelative(-8f, 7f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                quadTo(2f, 18.825f, 2f, 18f)
                verticalLineTo(6f)
                quadToRelative(0f, -0.825f, 0.588f, -1.412f)
                quadTo(3.175f, 4f, 4f, 4f)
                horizontalLineToRelative(16f)
                quadToRelative(0.825f, 0f, 1.413f, 0.588f)
                quadTo(22f, 5.175f, 22f, 6f)
                verticalLineToRelative(12f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(20.825f, 20f, 20f, 20f)
                close()
                moveToRelative(1.75f, -2f)
                quadToRelative(1.125f, -1.4f, 2.725f, -2.2f)
                quadToRelative(1.6f, -0.8f, 3.525f, -0.8f)
                quadToRelative(1.925f, 0f, 3.525f, 0.8f)
                quadToRelative(1.6f, 0.8f, 2.725f, 2.2f)
                horizontalLineTo(20f)
                verticalLineTo(6f)
                horizontalLineTo(4f)
                verticalLineToRelative(12f)
                close()
                moveToRelative(2.95f, 0f)
                horizontalLineToRelative(6.6f)
                quadToRelative(-0.725f, -0.5f, -1.562f, -0.75f)
                quadTo(12.9f, 17f, 12f, 17f)
                reflectiveQuadToRelative(-1.737f, 0.25f)
                quadToRelative(-0.838f, 0.25f, -1.563f, 0.75f)
                close()
                moveToRelative(3.3f, -7f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(11f, 10.425f, 11f, 10f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(11.575f, 9f, 12f, 9f)
                reflectiveQuadToRelative(0.713f, 0.287f)
                quadTo(13f, 9.575f, 13f, 10f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(12.425f, 11f, 12f, 11f)
                close()
                moveToRelative(0f, 1f)
                close()
            }
        }.build()
    }
}
