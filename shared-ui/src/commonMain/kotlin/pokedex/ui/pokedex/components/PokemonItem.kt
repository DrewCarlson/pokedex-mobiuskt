package pokedex.ui.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.drewcarlson.`shared-ui`.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import pokedex.model.Pokemon
import pokedex.ui.home.components.AsyncImage
import pokedex.ui.theme.*


@Composable
internal fun PokemonItem(
    onClick: () -> Unit,
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
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
                .background(color = pokemon.composeColor())
                .padding(10.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(Res.drawable.pokeballbackground),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.matchParentSize()
                )

                AsyncImage(
                    url = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .aspectRatio(1.2f)
                        .fillMaxHeight()
                )
            }

            Spacer(Modifier.height(14.dp))

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(.8f)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = pokemon.idString,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.4f), shape = Shapes.extraLarge)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    }
}