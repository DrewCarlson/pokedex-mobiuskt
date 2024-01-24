package pokedex.ui.favorite.loop

import pokedex.core.model.Pokemon

sealed class FavoriteEvent {
    data class PokemonListLoaded(val pokemonList: List<Pokemon>) : FavoriteEvent()
    data class PokemonListFailed(val error: String?) : FavoriteEvent()
    data object NavigateBack : FavoriteEvent()
    data class NavigateToDetails(val name: String) : FavoriteEvent()
}