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
fun rememberContactPhone(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "contact_phone",
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
                moveTo(2f, 21f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                quadTo(0f, 19.825f, 0f, 19f)
                verticalLineTo(5f)
                quadToRelative(0f, -0.825f, 0.588f, -1.413f)
                quadTo(1.175f, 3f, 2f, 3f)
                horizontalLineToRelative(20f)
                quadToRelative(0.825f, 0f, 1.413f, 0.587f)
                quadTo(24f, 4.175f, 24f, 5f)
                verticalLineToRelative(14f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(22.825f, 21f, 22f, 21f)
                close()
                moveToRelative(13.9f, -2f)
                horizontalLineTo(22f)
                verticalLineTo(5f)
                horizontalLineTo(2f)
                verticalLineToRelative(14f)
                horizontalLineToRelative(0.1f)
                quadToRelative(1.05f, -1.875f, 2.9f, -2.938f)
                quadTo(6.85f, 15f, 9f, 15f)
                reflectiveQuadToRelative(4f, 1.062f)
                quadToRelative(1.85f, 1.063f, 2.9f, 2.938f)
                close()
                moveTo(9f, 14f)
                quadToRelative(1.25f, 0f, 2.125f, -0.875f)
                reflectiveQuadTo(12f, 11f)
                quadToRelative(0f, -1.25f, -0.875f, -2.125f)
                reflectiveQuadTo(9f, 8f)
                quadToRelative(-1.25f, 0f, -2.125f, 0.875f)
                reflectiveQuadTo(6f, 11f)
                quadToRelative(0f, 1.25f, 0.875f, 2.125f)
                reflectiveQuadTo(9f, 14f)
                close()
                moveToRelative(9.275f, 3.375f)
                quadToRelative(0.275f, 0.275f, 0.7f, 0.25f)
                quadToRelative(0.425f, -0.025f, 0.7f, -0.3f)
                lineToRelative(0.7f, -0.7f)
                quadToRelative(0.275f, -0.275f, 0.3f, -0.65f)
                quadToRelative(0.025f, -0.375f, -0.2f, -0.675f)
                lineToRelative(-0.675f, -0.9f)
                quadToRelative(-0.125f, -0.2f, -0.338f, -0.3f)
                quadTo(19.25f, 14f, 19f, 14f)
                horizontalLineToRelative(-1.15f)
                quadToRelative(-0.15f, -0.45f, -0.25f, -0.963f)
                quadToRelative(-0.1f, -0.512f, -0.1f, -1.037f)
                quadToRelative(0f, -0.525f, 0.1f, -1.012f)
                quadToRelative(0.1f, -0.488f, 0.25f, -0.988f)
                horizontalLineTo(19f)
                quadToRelative(0.25f, 0f, 0.462f, -0.1f)
                quadToRelative(0.213f, -0.1f, 0.338f, -0.3f)
                lineToRelative(0.675f, -0.9f)
                quadToRelative(0.225f, -0.3f, 0.2f, -0.675f)
                quadToRelative(-0.025f, -0.375f, -0.3f, -0.65f)
                lineToRelative(-0.7f, -0.7f)
                quadToRelative(-0.275f, -0.275f, -0.7f, -0.3f)
                quadToRelative(-0.425f, -0.025f, -0.7f, 0.25f)
                quadToRelative(-1.05f, 1.05f, -1.662f, 2.463f)
                quadTo(16f, 10.5f, 16f, 12f)
                reflectiveQuadToRelative(0.613f, 2.912f)
                quadToRelative(0.612f, 1.413f, 1.662f, 2.463f)
                close()
                moveTo(4.55f, 19f)
                horizontalLineToRelative(8.9f)
                quadToRelative(-0.85f, -0.95f, -2.012f, -1.475f)
                quadTo(10.275f, 17f, 9f, 17f)
                quadToRelative(-1.275f, 0f, -2.425f, 0.525f)
                reflectiveQuadTo(4.55f, 19f)
                close()
                moveTo(9f, 12f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(8f, 11.425f, 8f, 11f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(8.575f, 10f, 9f, 10f)
                reflectiveQuadToRelative(0.713f, 0.287f)
                quadTo(10f, 10.575f, 10f, 11f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(9.425f, 12f, 9f, 12f)
                close()
                moveToRelative(3f, 0f)
                close()
            }
        }.build()
    }
}
