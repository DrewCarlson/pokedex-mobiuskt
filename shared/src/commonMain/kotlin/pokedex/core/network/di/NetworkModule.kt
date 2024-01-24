package pokedex.core.network.di

import pokedex.core.network.client.PokemonClient
import pokedex.core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module get() = { enableLogging ->
    module {
        single { createHttpClient(enableLogging) }
        single { PokemonClient(httpClient = get()) }
    }
}