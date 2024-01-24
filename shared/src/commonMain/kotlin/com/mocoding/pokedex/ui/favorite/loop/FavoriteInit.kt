package com.mocoding.pokedex.ui.favorite.loop

import kt.mobius.First
import kt.mobius.First.Companion.first
import kt.mobius.Init
import com.mocoding.pokedex.ui.favorite.loop.FavoriteEffect as Effect
import com.mocoding.pokedex.ui.favorite.loop.FavoriteModel as Model


class FavoriteInit : Init<Model, Effect> {
    override fun init(model: Model): First<Model, Effect> {
        return first(model, Effect.LoadFavorites)
    }
}
