package pokedex.ui.details.loop

import pokedex.data.repository.PokemonRepository
import kt.mobius.flow.ExecutionPolicy
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler

class DetailsEffectHandler(
    private val pokemonRepository: PokemonRepository
) : FlowTransformer<DetailsEffect, DetailsEvent> by subtypeEffectHandler({
    addFunction<DetailsEffect.LoadPokemonInfo> { (name) ->
        val result = pokemonRepository.getPokemonByName(name)
        if (result.isSuccess) {
            DetailsEvent.PokemonInfoLoaded(result.getOrThrow())
        } else {
            DetailsEvent.PokemonInfoFailed(result.exceptionOrNull()?.message)
        }
    }

    addConsumer<DetailsEffect.SetFavoriteState>(ExecutionPolicy.Latest) { (name, newState) ->
        pokemonRepository.updatePokemonFavoriteState(name, newState)
    }
})