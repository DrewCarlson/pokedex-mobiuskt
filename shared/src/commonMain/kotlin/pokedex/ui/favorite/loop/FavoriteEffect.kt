package pokedex.ui.favorite.loop

sealed class FavoriteEffect {
    data object LoadFavorites : FavoriteEffect()
}