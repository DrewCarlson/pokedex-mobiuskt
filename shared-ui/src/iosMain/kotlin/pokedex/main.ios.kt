package pokedex

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext
import pokedex.core.di.initKoin
import pokedex.ui.ContentView
import pokedex.ui.helper.LocalSafeArea
import pokedex.ui.theme.PokedexTheme
import platform.UIKit.*

@Suppress("unused", "FunctionName")
fun MainViewController(
    topSafeArea: Float,
    bottomSafeArea: Float
): UIViewController {
    val koinApp = initKoin()
    return ComposeUIViewController {
        val density = LocalDensity.current

        val topSafeAreaDp = with(density) { topSafeArea.toDp() }
        val bottomSafeAreaDp = with(density) { bottomSafeArea.toDp() }
        val safeArea = PaddingValues(top = topSafeAreaDp + 10.dp, bottom = bottomSafeAreaDp)

        // Bind safe area as the value for LocalSafeArea
        CompositionLocalProvider(LocalSafeArea provides safeArea) {
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
    }
}
