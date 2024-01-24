package pokedex.ui.home.loop

import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
import org.koin.core.annotation.Factory
import pokedex.ui.navigation.HomeNavigation
import pokedex.ui.home.loop.HomeEffect as Effect
import pokedex.ui.home.loop.HomeEvent as Event

@Factory
class HomeEffectHandler(
    private val navigation: HomeNavigation
) : FlowTransformer<Effect, Event> by subtypeEffectHandler({

    addAction<Effect.NavigateToPokedex>(navigation::navigateToPokedex)
})

