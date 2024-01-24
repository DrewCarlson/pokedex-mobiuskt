package pokedex.ui.favorite

import androidx.compose.runtime.Composable
import pokedex.ui.favorite.components.FavoriteContent
import pokedex.ui.utils.rememberMobiusLoop
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius
import pokedex.ui.favorite.loop.*

@Composable
internal fun FavoriteScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoop(FavoriteModel(), FavoriteInit()) {
        FlowMobius.loop(FavoriteUpdate(), get<FavoriteEffectHandler>())
            .logger(SimpleLogger("Favorite Loop"))
    }
    FavoriteContent(
        state = modelState.value,
        onEvent = eventConsumer,
    )
}