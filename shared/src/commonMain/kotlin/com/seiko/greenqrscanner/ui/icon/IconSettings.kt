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
fun rememberSettings(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "settings",
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
                moveTo(13.875f, 22f)
                horizontalLineToRelative(-3.75f)
                quadToRelative(-0.375f, 0f, -0.65f, -0.25f)
                reflectiveQuadToRelative(-0.325f, -0.625f)
                lineToRelative(-0.3f, -2.325f)
                quadToRelative(-0.325f, -0.125f, -0.612f, -0.3f)
                quadToRelative(-0.288f, -0.175f, -0.563f, -0.375f)
                lineToRelative(-2.175f, 0.9f)
                quadToRelative(-0.35f, 0.125f, -0.7f, 0.025f)
                reflectiveQuadToRelative(-0.55f, -0.425f)
                lineTo(2.4f, 15.4f)
                quadToRelative(-0.2f, -0.325f, -0.125f, -0.7f)
                quadToRelative(0.075f, -0.375f, 0.375f, -0.6f)
                lineToRelative(1.875f, -1.425f)
                quadTo(4.5f, 12.5f, 4.5f, 12.337f)
                verticalLineToRelative(-0.675f)
                quadToRelative(0f, -0.162f, 0.025f, -0.337f)
                lineTo(2.65f, 9.9f)
                quadToRelative(-0.3f, -0.225f, -0.375f, -0.6f)
                quadToRelative(-0.075f, -0.375f, 0.125f, -0.7f)
                lineToRelative(1.85f, -3.225f)
                quadToRelative(0.175f, -0.35f, 0.537f, -0.438f)
                quadToRelative(0.363f, -0.087f, 0.713f, 0.038f)
                lineToRelative(2.175f, 0.9f)
                quadToRelative(0.275f, -0.2f, 0.575f, -0.375f)
                quadToRelative(0.3f, -0.175f, 0.6f, -0.3f)
                lineToRelative(0.3f, -2.325f)
                quadToRelative(0.05f, -0.375f, 0.325f, -0.625f)
                reflectiveQuadToRelative(0.65f, -0.25f)
                horizontalLineToRelative(3.75f)
                quadToRelative(0.375f, 0f, 0.65f, 0.25f)
                reflectiveQuadToRelative(0.325f, 0.625f)
                lineToRelative(0.3f, 2.325f)
                quadToRelative(0.325f, 0.125f, 0.613f, 0.3f)
                quadToRelative(0.287f, 0.175f, 0.562f, 0.375f)
                lineToRelative(2.175f, -0.9f)
                quadToRelative(0.35f, -0.125f, 0.7f, -0.025f)
                reflectiveQuadToRelative(0.55f, 0.425f)
                lineTo(21.6f, 8.6f)
                quadToRelative(0.2f, 0.325f, 0.125f, 0.7f)
                quadToRelative(-0.075f, 0.375f, -0.375f, 0.6f)
                lineToRelative(-1.875f, 1.425f)
                quadToRelative(0.025f, 0.175f, 0.025f, 0.337f)
                verticalLineToRelative(0.675f)
                quadToRelative(0f, 0.163f, -0.05f, 0.338f)
                lineToRelative(1.875f, 1.425f)
                quadToRelative(0.3f, 0.225f, 0.375f, 0.6f)
                quadToRelative(0.075f, 0.375f, -0.125f, 0.7f)
                lineToRelative(-1.85f, 3.2f)
                quadToRelative(-0.2f, 0.325f, -0.562f, 0.438f)
                quadToRelative(-0.363f, 0.112f, -0.713f, -0.013f)
                lineToRelative(-2.125f, -0.9f)
                quadToRelative(-0.275f, 0.2f, -0.575f, 0.375f)
                quadToRelative(-0.3f, 0.175f, -0.6f, 0.3f)
                lineToRelative(-0.3f, 2.325f)
                quadToRelative(-0.05f, 0.375f, -0.325f, 0.625f)
                reflectiveQuadToRelative(-0.65f, 0.25f)
                close()
                moveToRelative(-1.825f, -6.5f)
                quadToRelative(1.45f, 0f, 2.475f, -1.025f)
                quadTo(15.55f, 13.45f, 15.55f, 12f)
                quadToRelative(0f, -1.45f, -1.025f, -2.475f)
                quadTo(13.5f, 8.5f, 12.05f, 8.5f)
                quadToRelative(-1.475f, 0f, -2.488f, 1.025f)
                quadTo(8.55f, 10.55f, 8.55f, 12f)
                quadToRelative(0f, 1.45f, 1.012f, 2.475f)
                quadTo(10.575f, 15.5f, 12.05f, 15.5f)
                close()
                moveToRelative(0f, -2f)
                quadToRelative(-0.625f, 0f, -1.062f, -0.438f)
                quadToRelative(-0.438f, -0.437f, -0.438f, -1.062f)
                reflectiveQuadToRelative(0.438f, -1.062f)
                quadToRelative(0.437f, -0.438f, 1.062f, -0.438f)
                reflectiveQuadToRelative(1.063f, 0.438f)
                quadToRelative(0.437f, 0.437f, 0.437f, 1.062f)
                reflectiveQuadToRelative(-0.437f, 1.062f)
                quadToRelative(-0.438f, 0.438f, -1.063f, 0.438f)
                close()
                moveTo(12f, 12f)
                close()
                moveToRelative(-1f, 8f)
                horizontalLineToRelative(1.975f)
                lineToRelative(0.35f, -2.65f)
                quadToRelative(0.775f, -0.2f, 1.438f, -0.588f)
                quadToRelative(0.662f, -0.387f, 1.212f, -0.937f)
                lineToRelative(2.475f, 1.025f)
                lineToRelative(0.975f, -1.7f)
                lineToRelative(-2.15f, -1.625f)
                quadToRelative(0.125f, -0.35f, 0.175f, -0.738f)
                quadToRelative(0.05f, -0.387f, 0.05f, -0.787f)
                reflectiveQuadToRelative(-0.05f, -0.788f)
                quadToRelative(-0.05f, -0.387f, -0.175f, -0.737f)
                lineToRelative(2.15f, -1.625f)
                lineToRelative(-0.975f, -1.7f)
                lineToRelative(-2.475f, 1.05f)
                quadToRelative(-0.55f, -0.575f, -1.212f, -0.963f)
                quadToRelative(-0.663f, -0.387f, -1.438f, -0.587f)
                lineTo(13f, 4f)
                horizontalLineToRelative(-1.975f)
                lineToRelative(-0.35f, 2.65f)
                quadToRelative(-0.775f, 0.2f, -1.437f, 0.587f)
                quadToRelative(-0.663f, 0.388f, -1.213f, 0.938f)
                lineTo(5.55f, 7.15f)
                lineToRelative(-0.975f, 1.7f)
                lineToRelative(2.15f, 1.6f)
                quadToRelative(-0.125f, 0.375f, -0.175f, 0.75f)
                quadToRelative(-0.05f, 0.375f, -0.05f, 0.8f)
                quadToRelative(0f, 0.4f, 0.05f, 0.775f)
                reflectiveQuadToRelative(0.175f, 0.75f)
                lineToRelative(-2.15f, 1.625f)
                lineToRelative(0.975f, 1.7f)
                lineToRelative(2.475f, -1.05f)
                quadToRelative(0.55f, 0.575f, 1.213f, 0.962f)
                quadToRelative(0.662f, 0.388f, 1.437f, 0.588f)
                close()
            }
        }.build()
    }
}
