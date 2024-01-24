package pokedex.ui.home.loop

import kt.mobius.Next
import kt.mobius.Next.Companion.dispatch
import kt.mobius.Next.Companion.next
import kt.mobius.Next.Companion.noChange
import kt.mobius.Update
import kt.mobius.gen.GenerateUpdate
import pokedex.ui.home.loop.HomeEffect
import pokedex.ui.home.loop.HomeEvent
import pokedex.ui.home.loop.HomeModel
import pokedex.ui.home.loop.HomeEffect as Effect
import pokedex.ui.home.loop.HomeEvent as Event
import pokedex.ui.home.loop.HomeModel as Model

@GenerateUpdate
class HomeUpdate : Update<Model, Event, Effect>, HomeGeneratedUpdate {

    override fun clearSearch(model: Model): Next<Model, Effect> {
        return next(
            model.copy(
                searchQuery = "",
                isLoading = false,
                results = emptyList(),
                isLastPageLoaded = false,
            )
        )
    }

    override fun searchPokedex(
        model: Model,
        event: Event.SearchPokedex
    ): Next<Model, Effect> {
        val queryString = event.query.lowercase()
        return next(
            model.copy(
                searchQuery = queryString,
                isLoading = true,
                results = emptyList(),
            ),
            Effect.SearchPokedex(
                query = queryString,
                offset = model.results.size,
            )
        )
    }

    override fun onSearchComplete(
        model: Model,
        event: Event.OnSearchComplete
    ): Next<Model, Effect> {
        if (model.searchQuery != event.query) {
            return noChange()
        }
        return next(
            model.copy(
                isLoading = false,
                results = model.results + event.results,
                isLastPageLoaded = event.results.isEmpty()
            )
        )
    }

    override fun loadMoreResults(model: Model): Next<Model, Effect> {
        return if (model.searchQuery.isBlank() || model.isLoading || model.isLastPageLoaded) {
            noChange()
        } else {
            next(
                model.copy(isLoading = true),
                Effect.SearchPokedex(
                    query = model.searchQuery,
                    offset = model.results.size,
                )
            )
        }
    }

    override fun onSearchError(
        model: Model,
        event: Event.OnSearchError
    ): Next<Model, Effect> {
        return noChange()
    }

    override fun openComingSoon(model: Model): Next<Model, Effect> {
        return noChange()
    }

    override fun openFavorites(model: Model): Next<Model, Effect> {
        return noChange()
    }

    override fun openPokedex(model: Model): Next<Model, Effect> {
        return dispatch(Effect.NavigateToPokedex)
    }

    override fun navigateToDetails(
        model: Model,
        event: Event.NavigateToDetails
    ): Next<Model, Effect> {
        return dispatch(Effect.NavigateToDetails(event.name))
    }
}

