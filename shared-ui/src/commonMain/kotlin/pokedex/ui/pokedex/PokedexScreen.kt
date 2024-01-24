package pokedex.ui.pokedex

import androidx.compose.runtime.Composable
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius
import pokedex.ui.pokedex.components.PokedexContent
import pokedex.ui.utils.rememberMobiusLoop
import pokedex.ui.pokedex.loop.*

@Composable
internal fun PokedexScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoop(PokedexModel(), PokedexInit()) {
        FlowMobius.loop(PokedexUpdate(), get<PokedexEffectHandler>())
            .logger(SimpleLogger("Pokedex Screen"))
    }

    PokedexContent(
        state = modelState.value,
        onEvent = eventConsumer,
    )
}
