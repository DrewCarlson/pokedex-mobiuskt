package pokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pokedex.ui.root.RootContent
import pokedex.ui.theme.PokedexTheme
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ContentView(navigator: Navigator) {
    PokedexTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RootContent(navigator = navigator)
        }
    }
}