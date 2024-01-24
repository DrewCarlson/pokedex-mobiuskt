package pokedex.ui.pokedex.loop

import pokedex.core.model.Pokemon

data class PokedexModel(
    val isLoading: Boolean = false,
    val isLastPageLoaded: Boolean = false,
    val error: String? = null,
    val pokemonList: List<Pokemon> = emptyList(),
) {
    companion object {
        fun create(): PokedexModel {
            return PokedexModel()
        }
    }
}
