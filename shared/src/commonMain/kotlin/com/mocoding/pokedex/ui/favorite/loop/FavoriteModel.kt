package com.mocoding.pokedex.ui.favorite.loop

import com.mocoding.pokedex.core.model.Pokemon

data class FavoriteModel(
    val isLoading: Boolean = false,
    val error: String? = null,
    val pokemonList: List<Pokemon> = emptyList(),
)
