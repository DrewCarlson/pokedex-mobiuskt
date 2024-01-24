package pokedex.ui.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pokedex.core.model.Pokemon
import pokedex.ui.home.components.AsyncImage
import pokedex.ui.theme.*


@Composable
internal fun PokemonItem(
    onClick: () -> Unit,
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    val brush = remember {
        Brush.linearGradient(
            listOf(
                listOf(
                    Red300,
                    Red500,
                ),
                listOf(
                    Yellow300,
                    Yellow500,
                ),
                listOf(
                    Green300,
                    Green500,
                ),
                listOf(
                    Blue300,
                    Blue500,
                ),
            ).random()
        )
    }

    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(brush = brush, alpha = .4f)
                .padding(10.dp)
        ) {
            AsyncImage(
                url = pokemon.imageUrl,
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .fillMaxHeight()
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.alpha(.8f)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = pokemon.numberString,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alpha(.4f)
            )
        }
    }
}