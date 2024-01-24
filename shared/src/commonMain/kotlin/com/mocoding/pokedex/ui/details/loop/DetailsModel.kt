package com.mocoding.pokedex.ui.details.loop

import com.mocoding.pokedex.core.model.PokemonInfo

data class DetailsModel(
    val name: String,
    val isLoading: Boolean = false,
    val error: String? = null,
    val pokemonInfo: PokemonInfo? = null
)
