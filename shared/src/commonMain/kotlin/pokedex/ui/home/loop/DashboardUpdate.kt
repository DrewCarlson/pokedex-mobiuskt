package pokedex.ui.home.loop

import kt.mobius.Next
import kt.mobius.Next.Companion.next
import kt.mobius.Update
import kt.mobius.gen.GenerateUpdate
import pokedex.ui.home.loop.DashboardEffect as Effect
import pokedex.ui.home.loop.DashboardEvent as Event
import pokedex.ui.home.loop.DashboardModel as Model

@GenerateUpdate
class DashboardUpdate : Update<Model, Event, Effect>, DashboardGeneratedUpdate {
    override fun onSearchQueryChanged(
        model: Model,
        event: Event.OnSearchQueryChanged
    ): Next<Model, Effect> {
        return next(model.copy(searchQuery = event.query))
    }
}

