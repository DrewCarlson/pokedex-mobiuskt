package pokedex.core.database.di

import pokedex.core.database.createDatabase
import pokedex.core.database.dao.PokemonDao
import pokedex.core.database.dao.PokemonInfoDao
import pokedex.core.database.sqlDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    factory { sqlDriverFactory() }
    single { createDatabase(driver = get()) }
    single { PokemonDao(pokemonDatabase = get()) }
    single { PokemonInfoDao(pokemonDatabase = get()) }
}