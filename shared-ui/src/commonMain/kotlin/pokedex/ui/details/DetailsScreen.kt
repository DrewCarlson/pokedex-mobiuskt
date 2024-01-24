package pokedex.ui.details

import androidx.compose.runtime.Composable
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius
import pokedex.ui.details.components.DetailsContent
import pokedex.ui.utils.rememberMobiusLoop
import pokedex.ui.details.loop.*

@Composable
internal fun DetailsScreen(name: String) {
    val (modelState, eventConsumer) = rememberMobiusLoop(DetailsModel(name), DetailsInit()) {
        FlowMobius.loop(DetailsUpdate(), get<DetailsEffectHandler>())
            .logger(SimpleLogger("Details Screen"))
    }

    DetailsContent(
        model = modelState.value,
        onEvent = eventConsumer,
    )
}
