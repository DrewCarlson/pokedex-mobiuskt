package com.mocoding.pokedex.ui.pokedex.loop

import com.mocoding.pokedex.core.model.Pokemon

sealed class PokedexEvent {

    data object PokemonListLoading : PokedexEvent()
    data class PokemonListLoaded(val pokemonList: List<Pokemon>) : PokedexEvent()
    data class PokemonListFailed(val error: String?) : PokedexEvent()
    data class SearchValueUpdated(val searchValue: String) : PokedexEvent()
    data object LastPageLoaded : PokedexEvent()

    data object LoadPokemonList : PokedexEvent()

    data object NavigateBack : PokedexEvent()
    data class NavigateToDetails(val name: String) : PokedexEvent()
}