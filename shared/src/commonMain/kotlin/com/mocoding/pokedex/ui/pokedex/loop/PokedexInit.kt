package com.mocoding.pokedex.ui.pokedex.loop

import kt.mobius.First
import kt.mobius.First.Companion.first
import kt.mobius.Init
import com.mocoding.pokedex.ui.pokedex.loop.PokedexEffect as Effect
import com.mocoding.pokedex.ui.pokedex.loop.PokedexModel as Model

class PokedexInit : Init<Model, Effect> {
    override fun init(model: Model): First<Model, Effect> {
        return if (model.pokemonList.isEmpty()) {
            first(model, Effect.LoadPokemonListByPage(0))
        } else {
            first(model)
        }
    }
}
