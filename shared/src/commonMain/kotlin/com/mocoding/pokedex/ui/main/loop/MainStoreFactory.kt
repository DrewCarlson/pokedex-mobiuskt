package com.mocoding.pokedex.ui.main.loop

import kt.mobius.Next
import kt.mobius.Next.Companion.next
import kt.mobius.Update
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
import kt.mobius.gen.GenerateUpdate
import com.mocoding.pokedex.ui.main.loop.DashboardEffect as Effect
import com.mocoding.pokedex.ui.main.loop.DashboardEvent as Event
import com.mocoding.pokedex.ui.main.loop.DashboardModel as Model

@GenerateUpdate
class DashboardUpdate : Update<Model, Event, Effect>, DashboardGeneratedUpdate {
    override fun onSearchQueryChanged(
        model: Model,
        event: Event.OnSearchQueryChanged
    ): Next<Model, Effect> {
        return next(model.copy(searchQuery = event.query))
    }
}

class DashboardEffectHandler() : FlowTransformer<Effect, Event> by subtypeEffectHandler({

})