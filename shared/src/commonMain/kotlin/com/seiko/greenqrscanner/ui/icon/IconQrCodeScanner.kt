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
fun rememberQrCodeScanner(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "qr_code_scanner",
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
                moveTo(3f, 7f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.287f)
                quadTo(2f, 6.425f, 2f, 6f)
                verticalLineTo(3f)
                quadToRelative(0f, -0.425f, 0.288f, -0.713f)
                quadTo(2.575f, 2f, 3f, 2f)
                horizontalLineToRelative(3f)
                quadToRelative(0.425f, 0f, 0.713f, 0.287f)
                quadTo(7f, 2.575f, 7f, 3f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(6.425f, 4f, 6f, 4f)
                horizontalLineTo(4f)
                verticalLineToRelative(2f)
                quadToRelative(0f, 0.425f, -0.287f, 0.713f)
                quadTo(3.425f, 7f, 3f, 7f)
                close()
                moveToRelative(0f, 15f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(2f, 21.425f, 2f, 21f)
                verticalLineToRelative(-3f)
                quadToRelative(0f, -0.425f, 0.288f, -0.712f)
                quadTo(2.575f, 17f, 3f, 17f)
                reflectiveQuadToRelative(0.713f, 0.288f)
                quadTo(4f, 17.575f, 4f, 18f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(2f)
                quadToRelative(0.425f, 0f, 0.713f, 0.288f)
                quadTo(7f, 20.575f, 7f, 21f)
                reflectiveQuadToRelative(-0.287f, 0.712f)
                quadTo(6.425f, 22f, 6f, 22f)
                close()
                moveToRelative(15f, 0f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(17f, 21.425f, 17f, 21f)
                reflectiveQuadToRelative(0.288f, -0.712f)
                quadTo(17.575f, 20f, 18f, 20f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(-2f)
                quadToRelative(0f, -0.425f, 0.288f, -0.712f)
                quadTo(20.575f, 17f, 21f, 17f)
                reflectiveQuadToRelative(0.712f, 0.288f)
                quadTo(22f, 17.575f, 22f, 18f)
                verticalLineToRelative(3f)
                quadToRelative(0f, 0.425f, -0.288f, 0.712f)
                quadTo(21.425f, 22f, 21f, 22f)
                close()
                moveToRelative(3f, -15f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.287f)
                quadTo(20f, 6.425f, 20f, 6f)
                verticalLineTo(4f)
                horizontalLineToRelative(-2f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(17f, 3.425f, 17f, 3f)
                reflectiveQuadToRelative(0.288f, -0.713f)
                quadTo(17.575f, 2f, 18f, 2f)
                horizontalLineToRelative(3f)
                quadToRelative(0.425f, 0f, 0.712f, 0.287f)
                quadTo(22f, 2.575f, 22f, 3f)
                verticalLineToRelative(3f)
                quadToRelative(0f, 0.425f, -0.288f, 0.713f)
                quadTo(21.425f, 7f, 21f, 7f)
                close()
                moveToRelative(-3.5f, 10.5f)
                horizontalLineTo(19f)
                verticalLineTo(19f)
                horizontalLineToRelative(-1.5f)
                close()
                moveToRelative(0f, -3f)
                horizontalLineTo(19f)
                verticalLineTo(16f)
                horizontalLineToRelative(-1.5f)
                close()
                moveTo(16f, 16f)
                horizontalLineToRelative(1.5f)
                verticalLineToRelative(1.5f)
                horizontalLineTo(16f)
                close()
                moveToRelative(-1.5f, 1.5f)
                horizontalLineTo(16f)
                verticalLineTo(19f)
                horizontalLineToRelative(-1.5f)
                close()
                moveTo(13f, 16f)
                horizontalLineToRelative(1.5f)
                verticalLineToRelative(1.5f)
                horizontalLineTo(13f)
                close()
                moveToRelative(3f, -3f)
                horizontalLineToRelative(1.5f)
                verticalLineToRelative(1.5f)
                horizontalLineTo(16f)
                close()
                moveToRelative(-1.5f, 1.5f)
                horizontalLineTo(16f)
                verticalLineTo(16f)
                horizontalLineToRelative(-1.5f)
                close()
                moveTo(13f, 13f)
                horizontalLineToRelative(1.5f)
                verticalLineToRelative(1.5f)
                horizontalLineTo(13f)
                close()
                moveToRelative(6f, -8f)
                verticalLineToRelative(6f)
                horizontalLineToRelative(-6f)
                verticalLineTo(5f)
                close()
                moveToRelative(-8f, 8f)
                verticalLineToRelative(6f)
                horizontalLineTo(5f)
                verticalLineToRelative(-6f)
                close()
                moveToRelative(0f, -8f)
                verticalLineToRelative(6f)
                horizontalLineTo(5f)
                verticalLineTo(5f)
                close()
                moveTo(9.5f, 17.5f)
                verticalLineToRelative(-3f)
                horizontalLineToRelative(-3f)
                verticalLineToRelative(3f)
                close()
                moveToRelative(0f, -8f)
                verticalLineToRelative(-3f)
                horizontalLineToRelative(-3f)
                verticalLineToRelative(3f)
                close()
                moveToRelative(8f, 0f)
                verticalLineToRelative(-3f)
                horizontalLineToRelative(-3f)
                verticalLineToRelative(3f)
                close()
            }
        }.build()
    }
}
