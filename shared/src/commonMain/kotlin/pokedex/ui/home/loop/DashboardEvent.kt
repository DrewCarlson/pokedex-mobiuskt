package pokedex.ui.home.loop

sealed class DashboardEvent {
    data class OnSearchQueryChanged(val query: String) : DashboardEvent()
    data object OpenPokedex : DashboardEvent()
    data object OpenFavorites : DashboardEvent()
    data object OpenComingSoon : DashboardEvent()
    //data class PokedexSearchSubmitted(val searchValue: String) : DashboardEvent()
}