package pokedex

import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
import org.koin.dsl.binds
import org.koin.dsl.module
import pokedex.core.di.initKoin
import pokedex.ui.details.loop.DetailsEffectHandler
import pokedex.ui.navigation.DetailsNavigation
import pokedex.ui.favorite.loop.FavoriteEffectHandler
import pokedex.ui.home.loop.HomeEffectHandler
import pokedex.ui.navigation.HomeNavigation
import pokedex.ui.pokedex.loop.PokedexEffectHandler
import pokedex.ui.navigation.PokedexNavigation
import kotlin.reflect.KClass

object Dependencies {

    private lateinit var koinApp: KoinApplication

    fun initialize(navigation: Any) {
        koinApp = initKoin(enableNetworkLogs = true) {
            modules(
                module {
                    single { navigation } binds arrayOf(
                        HomeNavigation::class,
                        PokedexNavigation::class,
                        DetailsNavigation::class,
                    )
                }
            )
        }
    }


    fun <T : Any> get(clazz: KClass<T>): T {
        return koinApp.koin.get(clazz)
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