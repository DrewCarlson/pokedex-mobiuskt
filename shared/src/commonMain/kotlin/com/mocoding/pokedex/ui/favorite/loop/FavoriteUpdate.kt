package com.mocoding.pokedex.ui.favorite.loop

import kt.mobius.Next
import kt.mobius.Next.Companion.next
import kt.mobius.Next.Companion.noChange
import kt.mobius.Update
import kt.mobius.gen.GenerateUpdate
import com.mocoding.pokedex.ui.favorite.loop.FavoriteEffect as Effect
import com.mocoding.pokedex.ui.favorite.loop.FavoriteEvent as Event
import com.mocoding.pokedex.ui.favorite.loop.FavoriteModel as Model


@GenerateUpdate
class FavoriteUpdate : Update<Model, Event, Effect>, FavoriteGeneratedUpdate {

    override fun pokemonListLoaded(
        model: Model,
        event: Event.PokemonListLoaded
    ): Next<Model, Effect> {
        return next(
            model.copy(
                isLoading = false,
                pokemonList = event.pokemonList,
            )
        )
    }

    override fun pokemonListFailed(
        model: Model,
        event: Event.PokemonListFailed
    ): Next<Model, Effect> {
        return next(
            model.copy(
                isLoading = false,
                error = event.error,
            )
        )
    }

    override fun navigateBack(model: Model): Next<Model, Effect> {
        return noChange()
    }

    override fun navigateToDetails(
        model: Model,
        event: Event.NavigateToDetails
    ): Next<Model, Effect> {
        return noChange()
    }
}
