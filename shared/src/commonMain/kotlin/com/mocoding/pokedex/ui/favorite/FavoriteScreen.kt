package com.mocoding.pokedex.ui.favorite

import androidx.compose.runtime.Composable
import com.mocoding.pokedex.ui.favorite.components.FavoriteContent
import com.mocoding.pokedex.ui.favorite.loop.FavoriteEffectHandler
import com.mocoding.pokedex.ui.favorite.loop.FavoriteInit
import com.mocoding.pokedex.ui.favorite.loop.FavoriteModel
import com.mocoding.pokedex.ui.favorite.loop.FavoriteUpdate
import com.mocoding.pokedex.ui.utils.rememberMobiusLoopPreCompose
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius

@Composable
internal fun FavoriteScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoopPreCompose(FavoriteModel(), FavoriteInit()) {
        FlowMobius.loop(FavoriteUpdate(), FavoriteEffectHandler(get()))
            .logger(SimpleLogger("Favorite Screen"))
    }
    FavoriteContent(
        state = modelState.value,
        onEvent = eventConsumer,
    )
}