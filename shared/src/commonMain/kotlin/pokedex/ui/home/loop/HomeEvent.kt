package pokedex.ui.home.loop

import pokedex.model.Pokemon

sealed class HomeEvent {

    data object ClearSearch : HomeEvent()

    data class OnSearchComplete(
        val query: String,
        val results: List<Pokemon>
    ) : HomeEvent()

    data class OnSearchError(
        val message: String
    ) : HomeEvent()

    data object LoadMoreResults : HomeEvent()

    data object OpenPokedex : HomeEvent()

    data object OpenFavorites : HomeEvent()

    data object OpenComingSoon : HomeEvent()

    data class NavigateToDetails(val name: String) : HomeEvent()

    data class SearchPokedex(val query: String) : HomeEvent()
}