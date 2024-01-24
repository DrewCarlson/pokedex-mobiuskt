package pokedex.ui.pokedex.loop

sealed class PokedexEffect {
    data class LoadPokemonList(val offset: Int): PokedexEffect()

    data object NavigateBack : PokedexEffect()

    data class NavigateToDetails(val name: String) : PokedexEffect()
}
