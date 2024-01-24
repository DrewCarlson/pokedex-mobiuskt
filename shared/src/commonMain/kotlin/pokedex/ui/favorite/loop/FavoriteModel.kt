package pokedex.ui.favorite.loop

import pokedex.core.model.Pokemon

data class FavoriteModel(
    val isLoading: Boolean = false,
    val error: String? = null,
    val pokemonList: List<Pokemon> = emptyList(),
)
