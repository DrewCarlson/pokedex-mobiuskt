package pokedex.core.di

import pokedex.core.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module
import pokedex.ui.PresentationModule

fun initKoin(
    enableNetworkLogs: Boolean = false
) = initKoin(enableNetworkLogs = enableNetworkLogs) {}

fun initKoin(
    enableNetworkLogs: Boolean = false,
    appDeclaration: KoinAppDeclaration
) = startKoin {
    appDeclaration()
    modules(
        networkModule(enableNetworkLogs),
        PresentationModule().module
    )
}