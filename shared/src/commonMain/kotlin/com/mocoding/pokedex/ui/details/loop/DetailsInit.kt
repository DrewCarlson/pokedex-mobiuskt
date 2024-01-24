package com.mocoding.pokedex.ui.details.loop

import kt.mobius.First
import kt.mobius.First.Companion.first
import kt.mobius.Init
import com.mocoding.pokedex.ui.details.loop.DetailsEffect as Effect
import com.mocoding.pokedex.ui.details.loop.DetailsModel as Model

class DetailsInit : Init<Model, Effect> {

    override fun init(model: Model): First<Model, Effect> {
        return if (model.pokemonInfo == null) {
            first(
                model.copy(
                    isLoading = true,
                    error = null
                ),
                Effect.LoadPokemonInfo(model.name)
            )
        } else {
            first(model)
        }
    }
}