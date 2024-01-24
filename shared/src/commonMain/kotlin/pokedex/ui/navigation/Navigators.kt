package pokedex.ui.navigation

interface BackNavHandler {

    fun navigateBack()
}

interface DetailsNavHandler {

    fun navigateToDetails(name: String)
}

interface PokedexNavHandler {

    fun navigateToPokedex()
}

interface FavoritesNavHandler {

    fun navigateToFavorites()
}