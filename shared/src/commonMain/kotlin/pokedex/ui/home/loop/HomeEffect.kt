package pokedex.ui.home.loop

sealed class HomeEffect {

    data class SearchPokedex(
        val query: String,
        val offset: Int,
    ) : HomeEffect()

    data object NavigateToPokedex : HomeEffect()

    data class NavigateToDetails(
        val name: String
    ) : HomeEffect()
}
