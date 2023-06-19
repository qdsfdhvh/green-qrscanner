import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.seiko.greenqrscanner.ui.RouteScene
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App(
    navigator: Navigator = rememberNavigator(),
) {
    AppTheme {
        RouteScene(navigator)
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
            background = Color.White,
            onBackground = Color.Black,
            primary = Color(0xFFCAE4E9),
            onPrimary = Color.Black,
            surface = Color(0xFFFBFBFB),
            onSurface = Color.Black,
            secondary = Color(0xFFCAE5EA),
            onSecondary = Color.Black,
            secondaryContainer = Color(0xFFCAE4E9),
            onSecondaryContainer = Color.Black,
            tertiary = Color(0xFF1A1A1A),
            onTertiary = Color.White,
            surfaceVariant = Color.White,
            onSurfaceVariant = Color.Black,
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
