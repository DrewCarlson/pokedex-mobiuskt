package com.mocoding.pokedex.ui.details.loop

sealed class DetailsEffect {
    data class LoadPokemonInfo(
        val name: String
    ) : DetailsEffect()

    data class SetFavoriteState(
        val name: String,
        val isFavorite: Boolean
    ) : DetailsEffect()
}