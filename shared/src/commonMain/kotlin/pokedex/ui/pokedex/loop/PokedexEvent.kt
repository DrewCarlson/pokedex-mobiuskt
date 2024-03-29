package pokedex.ui.pokedex.loop

import pokedex.model.Pokemon

sealed class PokedexEvent {

    data class PokemonListLoaded(val pokemonList: List<Pokemon>) : PokedexEvent()
    data class PokemonListFailed(val error: String?) : PokedexEvent()

    data object LastPageLoaded : PokedexEvent()

    data object LoadPokemonList : PokedexEvent()

    data object NavigateBack : PokedexEvent()
    data class NavigateToDetails(val name: String) : PokedexEvent()
}