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

public val Icons.Rounded.TextAd: ImageVector
    get() {
        if (_textAd != null) {
            return _textAd!!
        }
        _textAd = Builder(
            name = "TextAd",
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
                moveTo(210.0f, 670.0f)
                horizontalLineToRelative(540.0f)
                verticalLineToRelative(-60.0f)
                lineTo(210.0f, 610.0f)
                verticalLineToRelative(60.0f)
                close()
                moveTo(210.0f, 510.0f)
                horizontalLineToRelative(540.0f)
                verticalLineToRelative(-60.0f)
                lineTo(210.0f, 450.0f)
                verticalLineToRelative(60.0f)
                close()
                moveTo(210.0f, 350.0f)
                horizontalLineToRelative(378.0f)
                verticalLineToRelative(-60.0f)
                lineTo(210.0f, 290.0f)
                verticalLineToRelative(60.0f)
                close()
                moveTo(140.0f, 800.0f)
                quadToRelative(-24.0f, 0.0f, -42.0f, -18.0f)
                reflectiveQuadToRelative(-18.0f, -42.0f)
                verticalLineToRelative(-520.0f)
                quadToRelative(0.0f, -24.0f, 18.0f, -42.0f)
                reflectiveQuadToRelative(42.0f, -18.0f)
                horizontalLineToRelative(680.0f)
                quadToRelative(24.0f, 0.0f, 42.0f, 18.0f)
                reflectiveQuadToRelative(18.0f, 42.0f)
                verticalLineToRelative(520.0f)
                quadToRelative(0.0f, 24.0f, -18.0f, 42.0f)
                reflectiveQuadToRelative(-42.0f, 18.0f)
                lineTo(140.0f, 800.0f)
                close()
                moveTo(140.0f, 740.0f)
                horizontalLineToRelative(680.0f)
                verticalLineToRelative(-520.0f)
                lineTo(140.0f, 220.0f)
                verticalLineToRelative(520.0f)
                close()
                moveTo(140.0f, 740.0f)
                verticalLineToRelative(-520.0f)
                verticalLineToRelative(520.0f)
                close()
            }
        }
            .build()
        return _textAd!!
    }

private var _textAd: ImageVector? = null
