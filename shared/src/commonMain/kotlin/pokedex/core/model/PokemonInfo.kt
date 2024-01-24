package pokedex.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import pokedex.graphql.fragment.PokemonStats
import kotlin.math.roundToInt

@Serializable
data class PokemonInfo(
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    @SerialName("base_experience") val experience: Long,
    val types: List<PokemonType>,
    val stats: List<StatsResponse>,
    val genus: String,
    val description: String,
    val isFavorite: Boolean = false
) {
    val idString
        get() = when (id.toString().length) {
            1 -> "#00$id"
            2 -> "#0$id"
            else -> "#$id"
        }

    val imageUrl: String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"

    @Serializable
    data class TypeResponse(
        val slot: Int,
        val type: Type
    )

    @Serializable
    data class Type(
        val name: String
    )

    @Serializable
    data class StatsResponse(
        @SerialName("base_stat")
        val value: Int,
        val stat: Stat
    ) {
        @Transient
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
    }

    @Serializable
    data class Stat(
        val name: String
    )
}
