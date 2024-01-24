package pokedex.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module
import pokedex.ui.PresentationModule

fun initKoin(
    dbUrl: String,
    enableNetworkLogs: Boolean = false,
    appDeclaration: KoinAppDeclaration
) = startKoin {
    appDeclaration()
    modules(
        networkModule(dbUrl, enableNetworkLogs),
        PresentationModule().module
    )
}