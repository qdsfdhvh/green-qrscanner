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
fun rememberUnknownDocument(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "unknown_document",
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
                moveTo(200f, 256f)
                verticalLineToRelative(640f)
                verticalLineToRelative(-640f)
                verticalLineToRelative(200f)
                verticalLineToRelative(-200f)
                close()
                moveToRelative(120f, 400f)
                horizontalLineToRelative(107f)
                quadToRelative(11f, -23f, 25.5f, -43f)
                reflectiveQuadToRelative(32.5f, -37f)
                horizontalLineTo(320f)
                quadToRelative(-17f, 0f, -28.5f, 11.5f)
                reflectiveQuadTo(280f, 616f)
                quadToRelative(0f, 17f, 11.5f, 28.5f)
                reflectiveQuadTo(320f, 656f)
                close()
                moveToRelative(0f, 160f)
                horizontalLineToRelative(83f)
                quadToRelative(-3f, -20f, -3f, -40f)
                reflectiveQuadToRelative(3f, -40f)
                horizontalLineToRelative(-83f)
                quadToRelative(-17f, 0f, -28.5f, 11.5f)
                reflectiveQuadTo(280f, 776f)
                quadToRelative(0f, 17f, 11.5f, 28.5f)
                reflectiveQuadTo(320f, 816f)
                close()
                moveTo(200f, 976f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(120f, 896f)
                verticalLineTo(256f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(200f, 176f)
                horizontalLineToRelative(287f)
                quadToRelative(16f, 0f, 30.5f, 6f)
                reflectiveQuadToRelative(25.5f, 17f)
                lineToRelative(194f, 194f)
                quadToRelative(11f, 11f, 17f, 25.5f)
                reflectiveQuadToRelative(6f, 30.5f)
                verticalLineToRelative(59f)
                quadToRelative(-19f, -6f, -39f, -9f)
                reflectiveQuadToRelative(-41f, -3f)
                verticalLineToRelative(-40f)
                horizontalLineTo(520f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(480f, 416f)
                verticalLineTo(256f)
                horizontalLineTo(200f)
                verticalLineToRelative(640f)
                horizontalLineToRelative(227f)
                quadToRelative(11f, 23f, 25.5f, 43f)
                reflectiveQuadToRelative(32.5f, 37f)
                horizontalLineTo(200f)
                close()
                moveToRelative(480f, -400f)
                quadToRelative(83f, 0f, 141.5f, 58.5f)
                reflectiveQuadTo(880f, 776f)
                quadToRelative(0f, 83f, -58.5f, 141.5f)
                reflectiveQuadTo(680f, 976f)
                quadToRelative(-83f, 0f, -141.5f, -58.5f)
                reflectiveQuadTo(480f, 776f)
                quadToRelative(0f, -83f, 58.5f, -141.5f)
                reflectiveQuadTo(680f, 576f)
                close()
                moveToRelative(0f, 320f)
                quadToRelative(11f, 0f, 18.5f, -7.5f)
                reflectiveQuadTo(706f, 870f)
                quadToRelative(0f, -11f, -7.5f, -18.5f)
                reflectiveQuadTo(680f, 844f)
                quadToRelative(-11f, 0f, -18.5f, 7.5f)
                reflectiveQuadTo(654f, 870f)
                quadToRelative(0f, 11f, 7.5f, 18.5f)
                reflectiveQuadTo(680f, 896f)
                close()
                moveToRelative(0f, -240f)
                quadToRelative(-19f, 0f, -35f, 9.5f)
                reflectiveQuadTo(619f, 691f)
                quadToRelative(-4f, 6f, -1f, 12.5f)
                reflectiveQuadToRelative(10f, 9.5f)
                quadToRelative(6f, 3f, 12f, 0f)
                reflectiveQuadToRelative(11f, -8f)
                quadToRelative(5f, -7f, 12.5f, -11f)
                reflectiveQuadToRelative(16.5f, -4f)
                quadToRelative(13f, 0f, 22.5f, 8.5f)
                reflectiveQuadTo(712f, 720f)
                quadToRelative(0f, 11f, -6f, 18.5f)
                reflectiveQuadTo(692f, 754f)
                quadToRelative(-6f, 6f, -12.5f, 12f)
                reflectiveQuadTo(668f, 780f)
                quadToRelative(-3f, 6f, -4.5f, 12.5f)
                reflectiveQuadTo(662f, 806f)
                quadToRelative(0f, 9f, 5f, 16.5f)
                reflectiveQuadToRelative(13f, 7.5f)
                quadToRelative(8f, 0f, 13f, -6f)
                reflectiveQuadToRelative(5f, -14f)
                quadToRelative(0f, -11f, 6f, -19.5f)
                reflectiveQuadToRelative(14f, -16.5f)
                quadToRelative(12f, -11f, 21f, -24.5f)
                reflectiveQuadToRelative(9f, -29.5f)
                quadToRelative(0f, -27f, -20f, -45.5f)
                reflectiveQuadTo(680f, 656f)
                close()
            }
        }.build()
    }
}
