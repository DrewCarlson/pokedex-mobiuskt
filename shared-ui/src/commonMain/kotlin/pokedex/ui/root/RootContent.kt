package pokedex.ui.root

import androidx.compose.runtime.Composable
import pokedex.ui.comingsoon.ComingSoonScreen
import pokedex.ui.details.DetailsScreen
import pokedex.ui.favorite.FavoriteScreen
import pokedex.ui.home.HomeScreen
import pokedex.ui.pokedex.PokedexScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import org.koin.compose.koinInject

@Composable
fun RootContent(navigator: Navigator) {
    koinInject<PreComposeNavigation>().bind(navigator)
    NavHost(
        navigator = navigator,
        initialRoute = "/home",
    ) {
        scene("/home") {
            HomeScreen()
        }
        scene("/pokedex") {
            PokedexScreen()
        }
        scene("/favorite") {
            FavoriteScreen()
        }
        scene("/details/{name}") {
            val name = checkNotNull(it.path<String>("name")) {
                "Details route requires name parameter `/details/<name>`"
            }
            DetailsScreen(name = name)
        }
        scene("/coming-soon") {
            ComingSoonScreen(navigator)
        }
    }
}