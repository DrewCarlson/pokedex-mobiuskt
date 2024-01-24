package com.mocoding.pokedex.ui.details

import androidx.compose.runtime.Composable
import com.mocoding.pokedex.ui.details.components.DetailsContent
import com.mocoding.pokedex.ui.details.loop.DetailsEffectHandler
import com.mocoding.pokedex.ui.details.loop.DetailsInit
import com.mocoding.pokedex.ui.details.loop.DetailsModel
import com.mocoding.pokedex.ui.details.loop.DetailsUpdate
import com.mocoding.pokedex.ui.utils.rememberMobiusLoopPreCompose
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