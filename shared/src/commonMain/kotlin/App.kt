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
            primary = Color(0xFFCAE4E9),
            onPrimary = Color.Black,
            secondary = Color(0xFFCAE5EA),
            onSecondary = Color.Black,
            tertiary = Color(0xFF1A1A1A),
            onTertiary = Color.White,
            surface = Color(0xFFFBFBFB),
            onSurface = Color.Black,
            secondaryContainer = Color(0xFFCAE4E9),
            onSecondaryContainer = Color.Black,
            surfaceVariant = Color.White,
            onSurfaceVariant = Color.Black,
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
