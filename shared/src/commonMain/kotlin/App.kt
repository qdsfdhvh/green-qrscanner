import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.moriatsushi.insetsx.rememberWindowInsetsController
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.ui.RouteScene
import com.seiko.greenqrscanner.ui.theme.GreenQrScannerTheme
import com.seiko.greenqrscanner.ui.widget.LocalAppDateFormatter
import com.seiko.greenqrscanner.ui.widget.LocalWindowSizeClass
import com.seiko.greenqrscanner.util.AppDateFormatter
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun App(
    navigator: Navigator = rememberNavigator(),
) {
    CompositionLocalProvider(
        LocalWindowSizeClass provides calculateWindowSizeClass(),
        LocalAppDateFormatter provides rememberInject<AppDateFormatter>(),
    ) {
        val isSystemInDarkTheme = isSystemInDarkTheme()
        val useDarkMode by rememberSaveable { mutableStateOf(isSystemInDarkTheme) }

        val windowInsetsController = rememberWindowInsetsController()
        LaunchedEffect(useDarkMode) {
            windowInsetsController?.apply {
                setStatusBarContentColor(dark = !useDarkMode)
                setNavigationBarsContentColor(dark = !useDarkMode)
            }
        }

        GreenQrScannerTheme(useDarkMode) {
            RouteScene(navigator)
        }
    }
}
