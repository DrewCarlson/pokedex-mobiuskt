package pokedex.ui.details.loop

import pokedex.core.model.PokemonInfo

sealed class DetailsEvent {
    data class PokemonInfoLoaded(val pokemon: PokemonInfo) : DetailsEvent()
    data class PokemonInfoFailed(val error: String?) : DetailsEvent()

    data object ToggleFavorite : DetailsEvent()

    data object NavigateBack : DetailsEvent()
}