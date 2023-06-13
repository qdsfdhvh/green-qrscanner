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
fun rememberMoreVert(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "more_vert",
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
                moveTo(12f, 20f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                quadTo(10f, 18.825f, 10f, 18f)
                quadToRelative(0f, -0.825f, 0.588f, -1.413f)
                quadTo(11.175f, 16f, 12f, 16f)
                reflectiveQuadToRelative(1.413f, 0.587f)
                quadTo(14f, 17.175f, 14f, 18f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(12.825f, 20f, 12f, 20f)
                close()
                moveToRelative(0f, -6f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.588f)
                quadTo(10f, 12.825f, 10f, 12f)
                reflectiveQuadToRelative(0.588f, -1.413f)
                quadTo(11.175f, 10f, 12f, 10f)
                reflectiveQuadToRelative(1.413f, 0.587f)
                quadTo(14f, 11.175f, 14f, 12f)
                quadToRelative(0f, 0.825f, -0.587f, 1.412f)
                quadTo(12.825f, 14f, 12f, 14f)
                close()
                moveToRelative(0f, -6f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.588f)
                quadTo(10f, 6.825f, 10f, 6f)
                reflectiveQuadToRelative(0.588f, -1.412f)
                quadTo(11.175f, 4f, 12f, 4f)
                reflectiveQuadToRelative(1.413f, 0.588f)
                quadTo(14f, 5.175f, 14f, 6f)
                reflectiveQuadToRelative(-0.587f, 1.412f)
                quadTo(12.825f, 8f, 12f, 8f)
                close()
            }
        }.build()
    }
}
