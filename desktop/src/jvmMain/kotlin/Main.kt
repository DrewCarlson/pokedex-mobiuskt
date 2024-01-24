import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import pokedex.core.di.initKoin
import pokedex.ui.ContentView
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext
import org.koin.ksp.generated.module
import pokedex.ui.root.UiModule
import java.awt.Dimension

fun main() {
    val koinApp = initKoin(enableNetworkLogs = true) {
        modules(UiModule().module)
    }

    application {
        KoinContext(koinApp.koin) {
            PreComposeApp {
                val windowState = rememberWindowState(
                    position = WindowPosition(Alignment.Center),
                )
                Window(
                    title = "Pokédex",
                    state = windowState,
                    onCloseRequest = ::exitApplication,
                ) {
                    val navigator = rememberNavigator()
                    val density by rememberUpdatedState(LocalDensity.current)
                    LaunchedEffect(window) {
                        window.minimumSize = with(density) {
                            Dimension(430.dp.roundToPx(), 600.dp.roundToPx())
                        }
                    }
                    ContentView(navigator)
                }
            }
        }
    }
}
