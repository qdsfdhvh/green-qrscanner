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
fun rememberWifi(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "wifi",
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
                moveTo(5.3f, 14.275f)
                quadToRelative(-0.45f, -0.45f, -0.425f, -1.075f)
                quadToRelative(0.025f, -0.625f, 0.5f, -0.975f)
                quadToRelative(1.375f, -1.05f, 3.063f, -1.638f)
                quadTo(10.125f, 10f, 12f, 10f)
                quadToRelative(1.9f, 0f, 3.588f, 0.6f)
                quadToRelative(1.687f, 0.6f, 3.062f, 1.675f)
                quadToRelative(0.475f, 0.35f, 0.488f, 0.963f)
                quadToRelative(0.012f, 0.612f, -0.438f, 1.062f)
                quadToRelative(-0.425f, 0.425f, -1.037f, 0.438f)
                quadToRelative(-0.613f, 0.012f, -1.138f, -0.338f)
                quadToRelative(-0.975f, -0.675f, -2.112f, -1.037f)
                quadTo(13.275f, 13f, 12f, 13f)
                reflectiveQuadToRelative(-2.412f, 0.375f)
                quadTo(8.45f, 13.75f, 7.5f, 14.4f)
                quadToRelative(-0.55f, 0.375f, -1.162f, 0.338f)
                quadToRelative(-0.613f, -0.038f, -1.038f, -0.463f)
                close()
                moveTo(1.05f, 10.05f)
                quadTo(0.625f, 9.625f, 0.625f, 9.012f)
                quadToRelative(0f, -0.612f, 0.45f, -1.012f)
                quadToRelative(2.225f, -1.875f, 5f, -2.938f)
                quadTo(8.85f, 4f, 12f, 4f)
                reflectiveQuadToRelative(5.925f, 1.062f)
                quadToRelative(2.775f, 1.063f, 5f, 2.938f)
                quadToRelative(0.45f, 0.4f, 0.463f, 1f)
                quadToRelative(0.012f, 0.6f, -0.438f, 1.05f)
                quadToRelative(-0.425f, 0.425f, -1.037f, 0.45f)
                quadToRelative(-0.613f, 0.025f, -1.113f, -0.375f)
                quadToRelative(-1.825f, -1.475f, -4.062f, -2.3f)
                quadTo(14.5f, 7f, 12f, 7f)
                quadToRelative(-2.5f, 0f, -4.737f, 0.825f)
                quadToRelative(-2.238f, 0.825f, -4.063f, 2.3f)
                quadToRelative(-0.5f, 0.4f, -1.112f, 0.375f)
                quadToRelative(-0.613f, -0.025f, -1.038f, -0.45f)
                close()
                moveTo(12f, 20f)
                quadToRelative(-0.85f, 0f, -1.425f, -0.575f)
                quadTo(10f, 18.85f, 10f, 18f)
                quadToRelative(0f, -0.85f, 0.575f, -1.425f)
                quadTo(11.15f, 16f, 12f, 16f)
                quadToRelative(0.85f, 0f, 1.425f, 0.575f)
                quadTo(14f, 17.15f, 14f, 18f)
                quadToRelative(0f, 0.85f, -0.575f, 1.425f)
                quadTo(12.85f, 20f, 12f, 20f)
                close()
            }
        }.build()
    }
}
