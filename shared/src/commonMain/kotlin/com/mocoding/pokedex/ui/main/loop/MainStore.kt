package com.mocoding.pokedex.ui.main.loop

/*

        object PokedexClicked : Output()
        object FavoriteClicked : Output()
        object ComingSoon : Output()
        data class PokedexSearchSubmitted(val searchValue: String) : Output()
 */

data class DashboardModel(
    val searchQuery: String = "",
)

sealed class DashboardEvent {
    data class OnSearchQueryChanged(val query: String) : DashboardEvent()
}

sealed class DashboardEffect {

}