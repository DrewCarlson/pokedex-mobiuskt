package pokedex.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pokedex.core.model.PokemonType

@Composable
internal fun PokemonTypeSection(
    types: List<PokemonType>,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        types.forEach { type ->
            PokemonTypeBadge(name = type.name)
        }
    }
}