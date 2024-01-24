package com.mocoding.pokedex.ui.pokedex

import androidx.compose.runtime.Composable
import com.mocoding.pokedex.ui.pokedex.components.PokedexContent
import com.mocoding.pokedex.ui.pokedex.loop.PokedexEffectHandler
import com.mocoding.pokedex.ui.pokedex.loop.PokedexInit
import com.mocoding.pokedex.ui.pokedex.loop.PokedexModel
import com.mocoding.pokedex.ui.pokedex.loop.PokedexUpdate
import com.mocoding.pokedex.ui.utils.rememberMobiusLoopPreCompose
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius

@Composable
internal fun PokedexScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoopPreCompose(PokedexModel(), PokedexInit()) {
        FlowMobius.loop(PokedexUpdate(), PokedexEffectHandler(get()))
            .logger(SimpleLogger("Pokedex Screen"))
    }

    PokedexContent(
        state = modelState.value,
        onEvent = eventConsumer,
    )
}