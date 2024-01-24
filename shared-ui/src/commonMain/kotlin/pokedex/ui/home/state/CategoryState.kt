package pokedex.ui.home.state

import androidx.compose.ui.graphics.Color
import pokedex.ui.theme.*

data class CategoryState(
    val title: String,
    val iconUrl: String,
    val startColor: Color,
    val endColor: Color,
) {
    companion object {
        val pokedex = CategoryState(
            title = "Pokédex",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/pokeball.png",
            startColor = FireLight,
            endColor = FireLight,
        )

        val moves = CategoryState(
            title = "Moves",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/electric.png",
            startColor = ElectricLight,
            endColor = ElectricLight,
        )

        val evolutions = CategoryState(
            title = "Evolutions",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/dna.png",
            startColor = GrassLight,
            endColor = GrassLight,
        )

        val locations = CategoryState(
            title = "Locations",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/location.png",
            startColor = WaterLight,
            endColor = WaterLight,
        )
    }
}
