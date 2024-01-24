package pokedex.ui.home.loop

import pokedex.model.Pokemon

data class HomeModel(
    val searchQuery: String = "",
    val results: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLastPageLoaded: Boolean = false,
) {
    companion object {
        fun create(): HomeModel {
            return HomeModel()
        }
    }
}
