package com.mocoding.pokedex.ui.favorite.loop

import com.mocoding.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kt.mobius.flow.ExecutionPolicy
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
import com.mocoding.pokedex.ui.favorite.loop.FavoriteEffect as Effect
import com.mocoding.pokedex.ui.favorite.loop.FavoriteEvent as Event

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteEffectHandler(
    private val pokemonRepository: PokemonRepository
) : FlowTransformer<Effect, Event> by subtypeEffectHandler({

    addTransformer<Effect.LoadFavorites> { effects ->
        effects.flatMapLatest {
            pokemonRepository.getFavoritePokemonListFlow()
                .map { Event.PokemonListLoaded(it) }
        }
    }
})