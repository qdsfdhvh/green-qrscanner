package com.seiko.greenqrscanner.ui.scene.scan.widget

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun ScanCropView(
    color: Color = MaterialTheme.colorScheme.primary,
    cropSize: DpSize = DpSize(300.dp, 300.dp),
    lineWidth: Dp = 10.dp,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val lineYAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = with(LocalDensity.current) {
            cropSize.height.toPx()
        },
        animationSpec = infiniteRepeatable(
            animation = tween(2600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    Canvas(modifier) {
        // 绘制背景
        drawRect(Color.Transparent.copy(alpha = 0.2f))

        // 扫描框
        val cropWidth = cropSize.width.toPx()
        val cropHeight = cropSize.height.toPx()
        val topLeft = Offset(
            (size.width - cropWidth) / 2f,
            (size.height - cropHeight) / 2f,
        )
        val rectF = Rect(offset = topLeft, size = Size(cropWidth, cropHeight))
        drawRoundRect(
            color = Color.Transparent,
            topLeft = rectF.topLeft,
            size = rectF.size,
            blendMode = BlendMode.Clear,
        )

        // 扫描线
        val lineWidthPx = lineWidth.toPx()
        val rectLine = Rect(
            offset = topLeft.plus(Offset(x = lineWidthPx, y = lineYAnimation)),
            size = Size(cropWidth - 2 * lineWidthPx, 3.dp.toPx()),
        )
        drawOval(color, rectLine.topLeft, rectLine.size)

        // 角边框
        val lineWith = 3.dp.toPx()
        val lineLength = 12.dp.toPx()

        val lSizeH = Size(lineLength, lineWith)
        val lSizeV = Size(lineWith, lineLength)

        val path = Path().apply {
            // 左上角
            addRect(Rect(rectF.topLeft, lSizeH))
            addRect(Rect(rectF.topLeft, lSizeV))
            // 左下角
            addRect(Rect(rectF.bottomLeft.minus(Offset(x = 0f, y = lineWith)), lSizeH))
            addRect(Rect(rectF.bottomLeft.minus(Offset(x = 0f, y = lineLength)), lSizeV))
            // 右上角
            addRect(Rect(rectF.topRight.minus(Offset(x = lineLength, y = 0f)), lSizeH))
            addRect(Rect(rectF.topRight.minus(Offset(x = lineWith, y = 0f)), lSizeV))
            // 右下角
            addRect(Rect(rectF.bottomRight.minus(Offset(x = lineLength, y = lineWith)), lSizeH))
            addRect(Rect(rectF.bottomRight.minus(Offset(x = lineWith, y = lineLength)), lSizeV))
        }
        drawPath(path = path, color = Color.White)
    }
}
