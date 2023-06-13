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
fun rememberHome(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "home",
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
                moveTo(6f, 19f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(-6f)
                horizontalLineToRelative(6f)
                verticalLineToRelative(6f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(-9f)
                lineToRelative(-6f, -4.5f)
                lineTo(6f, 10f)
                close()
                moveToRelative(0f, 2f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                quadTo(4f, 19.825f, 4f, 19f)
                verticalLineToRelative(-9f)
                quadToRelative(0f, -0.475f, 0.213f, -0.9f)
                quadToRelative(0.212f, -0.425f, 0.587f, -0.7f)
                lineToRelative(6f, -4.5f)
                quadToRelative(0.275f, -0.2f, 0.575f, -0.3f)
                quadToRelative(0.3f, -0.1f, 0.625f, -0.1f)
                reflectiveQuadToRelative(0.625f, 0.1f)
                quadToRelative(0.3f, 0.1f, 0.575f, 0.3f)
                lineToRelative(6f, 4.5f)
                quadToRelative(0.375f, 0.275f, 0.588f, 0.7f)
                quadToRelative(0.212f, 0.425f, 0.212f, 0.9f)
                verticalLineToRelative(9f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(18.825f, 21f, 18f, 21f)
                horizontalLineToRelative(-5f)
                verticalLineToRelative(-6f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(6f)
                close()
                moveToRelative(6f, -8.75f)
                close()
            }
        }.build()
    }
}
