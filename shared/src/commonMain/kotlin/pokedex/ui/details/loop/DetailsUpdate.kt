package pokedex.ui.details.loop

import kt.mobius.Next
import kt.mobius.Next.Companion.dispatch
import kt.mobius.Next.Companion.next
import kt.mobius.Next.Companion.noChange
import kt.mobius.Update
import kt.mobius.gen.GenerateUpdate
import pokedex.ui.details.loop.DetailsEffect as Effect
import pokedex.ui.details.loop.DetailsEvent as Event
import pokedex.ui.details.loop.DetailsModel as Model

@GenerateUpdate
class DetailsUpdate : Update<Model, Event, Effect>, DetailsGeneratedUpdate {

    override fun navigateBack(model: Model): Next<Model, Effect> {
        return dispatch(Effect.NavigateBack)
    }

    override fun toggleFavorite(model: Model): Next<Model, Effect> {
        val pokemonInfo = model.pokemon
        if (model.isLoading || pokemonInfo == null) {
            return noChange()
        }
        /*val newState = !pokemonInfo.isFavorite
        return next(
            model.copy(
                pokemon = pokemonInfo.copy(
                    isFavorite = newState
                )
            ),
            Effect.SetFavoriteState(pokemonInfo.name, newState)
        )*/
        return noChange()
    }

    override fun pokemonInfoFailed(
        model: Model,
        event: Event.PokemonInfoFailed
    ): Next<Model, Effect> {
        return next(
            model.copy(
                isLoading = false,
                error = event.error,
            )
        )
    }

    override fun pokemonInfoLoaded(
        model: Model,
        event: Event.PokemonInfoLoaded
    ): Next<Model, Effect> {
        return next(
            model.copy(
                isLoading = false,
                error = null,
                pokemon = event.pokemon,
            )
        )
    }
}
