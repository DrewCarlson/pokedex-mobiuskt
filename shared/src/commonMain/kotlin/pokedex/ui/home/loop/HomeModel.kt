package pokedex.ui.home.loop

data class HomeModel(
    val searchQuery: String = "",
) {
    companion object {
        fun create(): HomeModel {
            return HomeModel()
        }
    }
}
