package pokedex.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import moe.tlaster.precompose.navigation.Navigator
import org.koin.core.annotation.Single
import pokedex.ui.navigation.DetailsNavigation
import pokedex.ui.navigation.HomeNavigation
import pokedex.ui.navigation.PokedexNavigation

@Single
class PreComposeNavigation : HomeNavigation,
    PokedexNavigation,
    DetailsNavigation {

    private var navigator: Navigator? = null

    @Composable
    fun bind(localNavigator: Navigator) {
        DisposableEffect(Unit) {
            navigator = localNavigator
            onDispose {
                if (navigator == localNavigator) {
                    navigator = null
                }
            }
        }
    }

    override fun navigateBack() {
        navigator?.goBack()
    }

    override fun navigateToDetails(name: String) {
        navigator?.navigate("/details/$name")
    }

    override fun navigateToPokedex() {
        navigator?.navigate("/pokedex")
    }
}
