package pokedex.ui.home.loop

import kt.mobius.First
import kt.mobius.First.Companion.first
import kt.mobius.Init
import pokedex.ui.home.loop.HomeEffect as Effect
import pokedex.ui.home.loop.HomeModel as Model

class HomeInit : Init<Model, Effect> {
    override fun init(model: Model): First<Model, Effect> {
        return first(model)
    }
}