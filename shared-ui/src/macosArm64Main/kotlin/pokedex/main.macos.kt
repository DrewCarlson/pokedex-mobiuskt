package pokedex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext
import platform.AppKit.NSApp
import platform.AppKit.NSApplication
import pokedex.core.di.initKoin
import pokedex.ui.ContentView
import pokedex.ui.theme.PokedexTheme

fun main() {
    NSApplication.sharedApplication()
    val koinApp = initKoin()
    Window("Pokédex") {
        KoinContext(koinApp.koin) {
            PreComposeApp {
                val navigator = rememberNavigator()
                PokedexTheme {
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ContentView(navigator)
                    }
                }
            }
        }
    }
    NSApp?.run()
}
