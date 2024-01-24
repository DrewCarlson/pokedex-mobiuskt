package pokedex.model

import pokedex.graphql.PokemonInfoByNameQuery
import pokedex.graphql.fragment.PokemonBasics

fun PokemonBasics.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        types = types.mapNotNull { t ->
            val type = t.type ?: return@mapNotNull null
            PokemonType(name = type.name)
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
            .cleanText(),
        types = pokemonBasics.types.mapNotNull { t ->
            val type = t.type ?: return@mapNotNull null
            PokemonType(name = type.name)
        },
        height = pokemonPhysical.height?.toLong() ?: -1,
        weight = pokemonPhysical.weight?.toLong() ?: -1,
        stats = pokemonStats.stats.map { stat ->
            PokemonStat(
                value = stat.base_stat,
                stat = PokemonStat.Stat(
                    name = stat.type?.name.orEmpty()
                )
            )
        }
    )
}

// Various API fields containing text are poorly formatted,
// this method corrects various issues I've seen.
private fun String.cleanText(): String {
    return replace('\n', ' ')
        .replace("\\", "")
}
