package pokedex.ui.pokedex.loop

import kt.mobius.Next
import kt.mobius.Next.Companion.dispatch
import kt.mobius.Next.Companion.next
import kt.mobius.Next.Companion.noChange
import kt.mobius.Update
import kt.mobius.gen.GenerateUpdate
import pokedex.ui.pokedex.loop.PokedexEffect as Effect
import pokedex.ui.pokedex.loop.PokedexEvent as Event
import pokedex.ui.pokedex.loop.PokedexModel as Model


@GenerateUpdate
class PokedexUpdate : Update<Model, Event, Effect>, PokedexGeneratedUpdate {
    override fun lastPageLoaded(model: Model): Next<Model, Effect> {
        return next(
            model.copy(
                isLastPageLoaded = true,
                isLoading = false,
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

    override fun pokemonListLoaded(
        model: Model,
        event: Event.PokemonListLoaded
    ): Next<Model, Effect> {
        return next(
            model.copy(
                isLoading = false,
                isLastPageLoaded = false,
                pokemonList = model.pokemonList + event.pokemonList,
            )
        )
    }

    override fun loadPokemonList(model: Model): Next<Model, Effect> {
        return if (model.isLoading || model.isLastPageLoaded) {
            noChange()
        } else {
            val offset = model.pokemonList.size
            next(
                model.copy(isLoading = true),
                Effect.LoadPokemonList(offset)
            )
        }
    }

    override fun navigateBack(model: Model): Next<Model, Effect> {
        return dispatch(Effect.NavigateBack)
    }

    override fun navigateToDetails(model: Model, event: Event.NavigateToDetails): Next<Model, Effect> {
        return dispatch(Effect.NavigateToDetails(event.name))
    }
}
