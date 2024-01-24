package pokedex.ui.favorite.loop

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
import org.koin.core.annotation.Factory
import pokedex.ui.favorite.loop.FavoriteEffect as Effect
import pokedex.ui.favorite.loop.FavoriteEvent as Event

@Factory
@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteEffectHandler(
) : FlowTransformer<Effect, Event> by subtypeEffectHandler({

    addTransformer<Effect.LoadFavorites> { effects ->
        effects.flatMapLatest {
            //pokemonRepository.getFavoritePokemonListFlow()
            //    .map { Event.PokemonListLoaded(it) }
            emptyFlow()
        }
    }
})