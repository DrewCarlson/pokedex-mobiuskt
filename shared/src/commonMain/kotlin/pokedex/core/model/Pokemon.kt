package pokedex.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
) {
    val idString
        get() = when {
            id < 10 -> "#00$id"
            id < 100 -> "#0$id"
            else -> "#$id"
        }

    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
}

@Serializable
data class PokemonType(
    val name: String,
    val slot: Int,
)
