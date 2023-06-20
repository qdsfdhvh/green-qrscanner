package androidx.compose.material.icons.rounded

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Icons.Rounded.Barcode: ImageVector
    get() {
        if (_barcode != null) {
            return _barcode!!
        }
        _barcode = Builder(
            name = "Barcode",
            defaultWidth = 48.0.dp,
            defaultHeight = 48.0.dp,
            viewportWidth = 960.0f,
            viewportHeight = 960.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveTo(40.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(85.0f)
                verticalLineToRelative(560.0f)
                lineTo(40.0f, 760.0f)
                close()
                moveTo(160.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(560.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(280.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(40.0f)
                verticalLineToRelative(560.0f)
                horizontalLineToRelative(-40.0f)
                close()
                moveTo(400.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(560.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(520.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(120.0f)
                verticalLineToRelative(560.0f)
                lineTo(520.0f, 760.0f)
                close()
                moveTo(680.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(40.0f)
                verticalLineToRelative(560.0f)
                horizontalLineToRelative(-40.0f)
                close()
                moveTo(800.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                horizontalLineToRelative(120.0f)
                verticalLineToRelative(560.0f)
                lineTo(800.0f, 760.0f)
                close()
            }
        }
            .build()
        return _barcode!!
    }

private var _barcode: ImageVector? = null
