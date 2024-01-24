package com.mocoding.pokedex.ui.pokedex.loop

sealed class PokedexEffect {

    data object NavigateBack : PokedexEffect()
    data class NavigateToDetails(val name: String) : PokedexEffect()

    data class LoadPokemonListByPage(val page: Long): PokedexEffect()
}