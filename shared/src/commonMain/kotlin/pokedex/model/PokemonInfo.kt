package pokedex.model

data class PokemonInfo(
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    val types: List<PokemonType>,
    val stats: List<PokemonStat>,
    val genus: String,
    val description: String,
    val isFavorite: Boolean = false
) {
    val idString = when {
        id < 10 -> "#00$id"
        id < 100 -> "#0$id"
        else -> "#$id"
    }

    val imageUrl: String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
}
