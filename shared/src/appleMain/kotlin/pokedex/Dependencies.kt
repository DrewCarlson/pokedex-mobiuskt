package pokedex

import org.koin.core.KoinApplication
import org.koin.dsl.module
import pokedex.di.initKoin
import pokedex.ui.details.loop.DetailsEffectHandler
import pokedex.ui.favorite.loop.FavoriteEffectHandler
import pokedex.ui.home.loop.HomeEffectHandler
import pokedex.ui.navigation.AppNavigation
import pokedex.ui.pokedex.loop.PokedexEffectHandler

object Dependencies {

    private lateinit var koinApp: KoinApplication

    fun initialize(navigation: AppNavigation) {
        koinApp = initKoin("pokedex.db", enableNetworkLogs = true) {
            modules(
                module {
                    single { navigation }
                }
            )
        }
    }

    fun getHomeHandler(): HomeEffectHandler {
        return koinApp.koin.get()
    }

    fun getPokedexHandler(): PokedexEffectHandler {
        return koinApp.koin.get()
    }

    fun getDetailsHandler(): DetailsEffectHandler {
        return koinApp.koin.get()
    }

    fun getFavoriteHandler(): FavoriteEffectHandler {
        return koinApp.koin.get()
    }
}
