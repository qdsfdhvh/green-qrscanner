import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.seiko.greenqrscanner.RouteScene

@Composable
fun App() {
    AppTheme {
        RouteScene()
    }
}

@Composable
private fun AppTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDark) {
        darkColorScheme(
            primary = Color(0xFFCAE4E9),
        )
    } else {
        lightColorScheme(
            surface = Color.Black,
            onSurface = Color.White,
            secondaryContainer = Color(0xFFCAE4E9),
            onSecondaryContainer = Color.Black,
            onSurfaceVariant = Color.White,
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
