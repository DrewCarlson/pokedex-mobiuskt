package pokedex.model


data class PokemonStat(
    val value: Int,
    val stat: Stat
) {
    val maxValue: Int = when (stat.name) {
        "hp" -> value * 2 + 204
        else -> value * 2 + 100
    }

    val name: String = when (stat.name) {
        "hp" -> "HP"
        "attack" -> "Attack"
        "defense" -> "Defense"
        "special-attack" -> "Sp. Atk"
        "special-defense" -> "Sp. Dep"
        "speed" -> "Speed"
        else -> stat.name
    }

    data class Stat(
        val name: String
    )
}
