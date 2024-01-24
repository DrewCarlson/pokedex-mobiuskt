package pokedex

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext
import org.koin.ksp.generated.module
import pokedex.di.initKoin
import pokedex.ui.ContentView
import pokedex.ui.helper.LocalSafeArea
import platform.UIKit.*
import pokedex.ui.root.UiModule

@Suppress("unused", "FunctionName")
fun MainViewController(
    topSafeArea: Float,
    bottomSafeArea: Float
): UIViewController {
    val koinApp = initKoin("pokedex.db") {
        modules(UiModule().module)
    }
    return ComposeUIViewController {
        val density = LocalDensity.current

        val topSafeAreaDp = with(density) { topSafeArea.toDp() }
        val bottomSafeAreaDp = with(density) { bottomSafeArea.toDp() }
        val safeArea = PaddingValues(top = topSafeAreaDp, bottom = bottomSafeAreaDp)

        //CompositionLocalProvider(LocalSafeArea provides safeArea) {
            KoinContext(koinApp.koin) {
                PreComposeApp {
                    val navigator = rememberNavigator()
                    ContentView(navigator)
                }
            }
        //}
    }
}
