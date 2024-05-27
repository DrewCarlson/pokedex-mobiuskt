package org.koin.ksp.generated

import org.koin.core.module.Module
import org.koin.dsl.*

// Temporary inclusion of generated code until koin-annotations supports wasm
// https://github.com/InsertKoinIO/koin-annotations/issues/120
public val pokedex_ui_PresentationModule : Module = module {
    factory() { pokedex.ui.details.loop.DetailsEffectHandler(apolloClient=get(),navigation=get()) } bind(kt.mobius.flow.FlowTransformer::class)
    factory() { pokedex.ui.favorite.loop.FavoriteEffectHandler(navigation=get()) } bind(kt.mobius.flow.FlowTransformer::class)
    factory() { pokedex.ui.home.loop.HomeEffectHandler(apolloClient=get(),navigation=get()) } bind(kt.mobius.flow.FlowTransformer::class)
    factory() { pokedex.ui.pokedex.loop.PokedexEffectHandler(apolloClient=get(),navigation=get()) } bind(kt.mobius.flow.FlowTransformer::class)
}
public val pokedex.ui.PresentationModule.module : org.koin.core.module.Module get() = pokedex_ui_PresentationModule