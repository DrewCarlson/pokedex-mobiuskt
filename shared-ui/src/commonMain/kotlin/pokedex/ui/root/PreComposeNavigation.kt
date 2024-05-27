package pokedex.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import moe.tlaster.precompose.navigation.Navigator
//import org.koin.core.annotation.Single
import pokedex.ui.navigation.AppNavigation

//@Single
class PreComposeNavigation : AppNavigation {

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

    override fun navigateToFavorites() {
        navigator?.navigate("/comingsoon")
    }
}
