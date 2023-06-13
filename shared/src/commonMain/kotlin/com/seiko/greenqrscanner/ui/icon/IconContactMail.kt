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
fun rememberContactMail(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "contact_mail",
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
                moveToRelative(6f, -3f)
                horizontalLineToRelative(5f)
                quadToRelative(0.425f, 0f, 0.712f, -0.288f)
                quadTo(21f, 10.425f, 21f, 10f)
                verticalLineTo(7f)
                quadToRelative(0f, -0.425f, -0.288f, -0.713f)
                quadTo(20.425f, 6f, 20f, 6f)
                horizontalLineToRelative(-5f)
                quadToRelative(-0.425f, 0f, -0.712f, 0.287f)
                quadTo(14f, 6.575f, 14f, 7f)
                verticalLineToRelative(3f)
                quadToRelative(0f, 0.425f, 0.288f, 0.712f)
                quadToRelative(0.287f, 0.288f, 0.712f, 0.288f)
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
                moveToRelative(5.5f, -2.25f)
                lineTo(15f, 8f)
                verticalLineTo(7f)
                lineToRelative(2.5f, 1.75f)
                lineTo(20f, 7f)
                verticalLineToRelative(1f)
                close()
            }
        }.build()
    }
}
