package pokedex.ui.home.loop

import kt.mobius.Next
import kt.mobius.Next.Companion.dispatch
import kt.mobius.Next.Companion.next
import kt.mobius.Next.Companion.noChange
import kt.mobius.Update
import kt.mobius.gen.GenerateUpdate
import pokedex.ui.home.loop.HomeEffect as Effect
import pokedex.ui.home.loop.HomeEvent as Event
import pokedex.ui.home.loop.HomeModel as Model

@GenerateUpdate
class HomeUpdate : Update<Model, Event, Effect>, HomeGeneratedUpdate {
    override fun openComingSoon(model: Model): Next<Model, Effect> {
        return noChange()
    }

    override fun openFavorites(model: Model): Next<Model, Effect> {
        return noChange()
    }

    override fun openPokedex(model: Model): Next<Model, Effect> {
        return dispatch(Effect.NavigateToPokedex)
    }

    override fun searchPokedex(
        model: Model,
        event: Event.SearchPokedex
    ): Next<Model, Effect> {
        // TODO: pass query from event
        return dispatch(
            Effect.NavigateToPokedex
        )
    }
}

