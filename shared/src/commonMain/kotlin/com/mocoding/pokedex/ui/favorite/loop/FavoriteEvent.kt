package com.mocoding.pokedex.ui.favorite.loop

import com.mocoding.pokedex.core.model.Pokemon

sealed class FavoriteEvent {
    data class PokemonListLoaded(val pokemonList: List<Pokemon>) : FavoriteEvent()
    data class PokemonListFailed(val error: String?) : FavoriteEvent()
    data object NavigateBack : FavoriteEvent()
    data class NavigateToDetails(val name: String) : FavoriteEvent()
}