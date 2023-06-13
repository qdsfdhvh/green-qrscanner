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
fun rememberSms(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "sms",
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
                moveTo(8f, 11f)
                quadToRelative(0.425f, 0f, 0.713f, -0.288f)
                quadTo(9f, 10.425f, 9f, 10f)
                reflectiveQuadToRelative(-0.287f, -0.713f)
                quadTo(8.425f, 9f, 8f, 9f)
                reflectiveQuadToRelative(-0.713f, 0.287f)
                quadTo(7f, 9.575f, 7f, 10f)
                reflectiveQuadToRelative(0.287f, 0.712f)
                quadTo(7.575f, 11f, 8f, 11f)
                close()
                moveToRelative(4f, 0f)
                quadToRelative(0.425f, 0f, 0.713f, -0.288f)
                quadTo(13f, 10.425f, 13f, 10f)
                reflectiveQuadToRelative(-0.287f, -0.713f)
                quadTo(12.425f, 9f, 12f, 9f)
                reflectiveQuadToRelative(-0.712f, 0.287f)
                quadTo(11f, 9.575f, 11f, 10f)
                reflectiveQuadToRelative(0.288f, 0.712f)
                quadTo(11.575f, 11f, 12f, 11f)
                close()
                moveToRelative(4f, 0f)
                quadToRelative(0.425f, 0f, 0.712f, -0.288f)
                quadTo(17f, 10.425f, 17f, 10f)
                reflectiveQuadToRelative(-0.288f, -0.713f)
                quadTo(16.425f, 9f, 16f, 9f)
                reflectiveQuadToRelative(-0.712f, 0.287f)
                quadTo(15f, 9.575f, 15f, 10f)
                reflectiveQuadToRelative(0.288f, 0.712f)
                quadTo(15.575f, 11f, 16f, 11f)
                close()
                moveTo(2f, 19.575f)
                verticalLineTo(4f)
                quadToRelative(0f, -0.825f, 0.588f, -1.413f)
                quadTo(3.175f, 2f, 4f, 2f)
                horizontalLineToRelative(16f)
                quadToRelative(0.825f, 0f, 1.413f, 0.587f)
                quadTo(22f, 3.175f, 22f, 4f)
                verticalLineToRelative(12f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(20.825f, 18f, 20f, 18f)
                horizontalLineTo(6f)
                lineToRelative(-2.3f, 2.3f)
                quadToRelative(-0.475f, 0.475f, -1.088f, 0.212f)
                quadTo(2f, 20.25f, 2f, 19.575f)
                close()
                moveToRelative(2f, -2.4f)
                lineTo(5.175f, 16f)
                horizontalLineTo(20f)
                verticalLineTo(4f)
                horizontalLineTo(4f)
                close()
                moveTo(4f, 4f)
                verticalLineToRelative(13.175f)
                close()
            }
        }.build()
    }
}
