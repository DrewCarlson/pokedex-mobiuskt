package pokedex.data

import pokedex.core.model.Pokemon
import pokedex.core.model.PokemonInfo
import commocodingpokedex.PokemonInfoEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pokedex.core.model.PokemonType
import pokedex.graphql.PokemonListQuery
import pokedex.graphql.PokemonInfoByNameQuery

fun PokemonListQuery.Pokemon.toPokemon(): Pokemon {
    return Pokemon(
        id = pokemonBasics.id,
        name = pokemonBasics.name,
        types = pokemonTypes.types.mapNotNull { t ->
            val type = t.type ?: return@mapNotNull null
            PokemonType(
                name = type.name,
                slot = -1,
            )
        }
    )
}

fun PokemonInfoByNameQuery.Pokemon.toPokemon(): Pokemon {
    return Pokemon(
        id = pokemonBasics.id,
        name = pokemonBasics.name,
        types = pokemonTypes.types.mapNotNull { t ->
            val type = t.type ?: return@mapNotNull null
            PokemonType(
                name = type.name,
                slot = -1,
            )
        }
    )
}

fun PokemonInfoByNameQuery.Pokemon.toPokemonInfo(): PokemonInfo {
    return PokemonInfo(
        id = pokemonBasics.id.toLong(),
        name = pokemonBasics.name,
        genus = pokemonSpecy.specy?.names?.firstOrNull()?.genus.orEmpty(),
        description = pokemonSpecy.specy
            ?.flavorText
            ?.firstOrNull()
            ?.flavor_text
            .orEmpty()
            .replace('\n', ' ')
            .replace("\\", ""),
        types = pokemonTypes.types.mapNotNull { t ->
            val type = t.type ?: return@mapNotNull null
            PokemonType(
                name = type.name,
                slot = -1,
            )
        },
        experience = -1L,
        height = pokemonPhysical.height?.toLong() ?: -1,
        weight = pokemonPhysical.weight?.toLong() ?: -1,
        stats = pokemonStats.stats.map { stat ->
            PokemonInfo.StatsResponse(
                value = stat.base_stat,
                stat = PokemonInfo.Stat(
                    name = stat.type?.name.orEmpty()
                )
            )
        }
    )
}
