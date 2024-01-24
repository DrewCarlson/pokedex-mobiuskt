package pokedex.ui.home.loop

sealed class HomeEvent {
    data object OpenPokedex : HomeEvent()
    data object OpenFavorites : HomeEvent()
    data object OpenComingSoon : HomeEvent()
    data class SearchPokedex(val query: String) : HomeEvent()
}