package pokedex.data.di

import pokedex.data.repository.PokemonRepository
import pokedex.data.repository.PokemonRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<PokemonRepository> {
        PokemonRepositoryImpl(
            pokemonClient = get(),
            pokemonDao = get(),
            pokemonInfoDao = get()
        )
    }
}