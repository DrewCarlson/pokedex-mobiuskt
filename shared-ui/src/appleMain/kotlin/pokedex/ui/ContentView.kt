package pokedex.ui

import androidx.compose.runtime.Composable
import pokedex.ui.root.RootContent
import moe.tlaster.precompose.navigation.Navigator

@Composable
internal fun ContentView(navigator: Navigator) {
    RootContent(navigator = navigator)
}