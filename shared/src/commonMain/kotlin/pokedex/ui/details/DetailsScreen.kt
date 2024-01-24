package pokedex.ui.details

import androidx.compose.runtime.Composable
import pokedex.ui.details.components.DetailsContent
import pokedex.ui.details.loop.DetailsEffectHandler
import pokedex.ui.details.loop.DetailsInit
import pokedex.ui.details.loop.DetailsModel
import pokedex.ui.details.loop.DetailsUpdate
import pokedex.ui.utils.rememberMobiusLoopPreCompose
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius

@Composable
internal fun DetailsScreen(name: String) {
    val (modelState, eventConsumer) = rememberMobiusLoopPreCompose(DetailsModel(name), DetailsInit()) {
        FlowMobius.loop(DetailsUpdate(), DetailsEffectHandler(get()))
            .logger(SimpleLogger("Details Screen"))
    }

    DetailsContent(
        state = modelState.value,
        onEvent = eventConsumer,
    )
}