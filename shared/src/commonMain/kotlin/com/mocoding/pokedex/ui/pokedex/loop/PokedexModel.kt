package com.mocoding.pokedex.ui.pokedex.loop

import com.mocoding.pokedex.core.model.Pokemon

data class PokedexModel(
    val isLoading: Boolean = false,
    val isLastPageLoaded: Boolean = false,
    val error: String? = null,
    val pokemonList: List<Pokemon> = emptyList(),
    val searchValue: String = "",
)
