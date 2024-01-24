package pokedex.core.di

import pokedex.core.database.di.databaseModule
import pokedex.core.network.di.networkModule
import pokedex.data.di.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    enableNetworkLogs: Boolean = false,
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    modules(
        databaseModule,
        networkModule(enableNetworkLogs),
        dataModule
    )
}