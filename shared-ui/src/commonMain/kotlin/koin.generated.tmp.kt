package org.koin.ksp.generated

import org.koin.core.module.Module
import org.koin.dsl.*


public val pokedex_ui_root_UiModule : Module = module {
    single() { pokedex.ui.root.PreComposeNavigation() } bind(pokedex.ui.navigation.AppNavigation::class)
}
public val pokedex.ui.root.UiModule.module : org.koin.core.module.Module get() = pokedex_ui_root_UiModule