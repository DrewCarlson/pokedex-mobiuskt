package pokedex.ui.home.state

import androidx.compose.ui.graphics.Color
import pokedex.ui.theme.*

data class CategoryState(
    val title: String,
    val color: Color,
) {
    companion object {
        val pokedex = CategoryState(
            title = "Pokédex",
            color = FireLight,
        )

        val moves = CategoryState(
            title = "Moves",
            color = ElectricLight,
        )

        val evolutions = CategoryState(
            title = "Evolutions",
            color = GrassLight,
        )

        val locations = CategoryState(
            title = "Locations",
            color = WaterLight,
        )
    }
}
