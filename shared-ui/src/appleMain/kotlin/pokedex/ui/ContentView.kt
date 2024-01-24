package pokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pokedex.ui.root.RootContent
import moe.tlaster.precompose.navigation.Navigator
import pokedex.ui.theme.PokedexTheme

@Composable
internal fun ContentView(navigator: Navigator) {
    PokedexTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            RootContent(navigator = navigator)
        }
    }
}