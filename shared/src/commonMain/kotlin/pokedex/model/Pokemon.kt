package pokedex.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
) {
    val idString = when {
        id < 10 -> "#00$id"
        id < 100 -> "#0$id"
        else -> "#$id"
    }

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
}
