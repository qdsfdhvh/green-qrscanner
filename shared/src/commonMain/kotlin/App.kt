
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
        GreenQrScannerTheme {
            RouteScene(navigator)
        }
        val transition = updateTransition(targetState = "", label = "AnimatedContent")
        transition.AnimatedContent {

        }
    }
}
