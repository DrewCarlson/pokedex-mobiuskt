package com.mocoding.pokedex.ui.pokedex.loop

import com.mocoding.pokedex.data.repository.PokemonRepository
import kt.mobius.flow.ExecutionPolicy
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler
import com.mocoding.pokedex.ui.pokedex.loop.PokedexEffect as Effect
import com.mocoding.pokedex.ui.pokedex.loop.PokedexEvent as Event

class PokedexEffectHandler(
    private val pokemonRepository: PokemonRepository
) : FlowTransformer<Effect, Event> by subtypeEffectHandler({
    addFunction<Effect.LoadPokemonListByPage>(ExecutionPolicy.Sequential) { (page) ->
        val result = pokemonRepository.getPokemonList(page)
        if (result.isSuccess) {
            val pokemonList = result.getOrThrow()
            if (pokemonList.isEmpty()) {
                Event.LastPageLoaded
            } else {
                Event.PokemonListLoaded(pokemonList)
            }
        } else {
            Event.PokemonListFailed(result.exceptionOrNull()?.message)
        }
    }
})
