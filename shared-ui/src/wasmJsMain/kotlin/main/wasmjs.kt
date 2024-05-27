import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext
import org.koin.ksp.generated.module
import pokedex.di.initKoin
import pokedex.ui.root.RootContent
import pokedex.ui.root.UiModule
import pokedex.ui.theme.PokedexTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val koinApp = initKoin("", enableNetworkLogs = true) {
        modules(UiModule().module)
    }
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        KoinContext(koinApp.koin) {
            PreComposeApp {
                val navigator = rememberNavigator()
                PokedexTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RootContent(navigator)
                    }
                }
            }
        }
    }
}