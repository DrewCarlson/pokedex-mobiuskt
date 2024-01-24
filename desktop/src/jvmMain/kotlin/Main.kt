import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mocoding.pokedex.core.di.initKoin
import com.mocoding.pokedex.ui.ContentView
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext

fun main() {
    val koinApp = initKoin(enableNetworkLogs = false)

    application {
        KoinContext(koinApp.koin) {
            PreComposeApp {
                val navigator = rememberNavigator()
                Window(
                    title = "Pokedex",
                    onCloseRequest = ::exitApplication
                ) {
                    ContentView(navigator)
                }
            }
        }
    }
}
