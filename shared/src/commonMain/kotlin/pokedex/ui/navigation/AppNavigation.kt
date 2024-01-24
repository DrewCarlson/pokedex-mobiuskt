package pokedex.ui.navigation

interface AppNavigation {

    fun navigateBack()

    fun navigateToDetails(name: String)

    fun navigateToPokedex()

    fun navigateToFavorites()
}
