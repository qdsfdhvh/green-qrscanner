
import androidx.compose.runtime.Composable
import com.seiko.greenqrscanner.ui.RouteScene
import com.seiko.greenqrscanner.ui.theme.GreenQrScannerTheme
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App(
    navigator: Navigator = rememberNavigator(),
) {
    GreenQrScannerTheme {
        RouteScene(navigator)
    }
}
