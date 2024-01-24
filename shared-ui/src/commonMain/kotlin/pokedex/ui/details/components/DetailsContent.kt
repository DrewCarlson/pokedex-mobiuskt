package pokedex.ui.details.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.drewcarlson.`shared-ui`.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import pokedex.ui.home.components.AsyncImage
import pokedex.ui.details.loop.DetailsEvent
import pokedex.ui.details.loop.DetailsModel
import pokedex.ui.helper.LocalSafeArea
import pokedex.ui.theme.composeColor


@Composable
internal fun DetailsContent(
    model: DetailsModel,
    onEvent: (DetailsEvent) -> Unit,
) {
    val scrollState = rememberScrollState()
    val primaryColor = model.pokemon?.composeColor() ?: MaterialTheme.colorScheme.background
    Scaffold(
        modifier = Modifier
            .padding(LocalSafeArea.current)
            .fillMaxSize(),
        containerColor = primaryColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { onEvent(DetailsEvent.NavigateBack) }
                    ) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                actions = {
                    /*IconButton(
                        onClick = { onEvent(DetailsEvent.ToggleFavorite) }
                    ) {
                        Icon(
                            if (state.pokemonInfo?.isFavorite == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (state.pokemonInfo?.isFavorite == true) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onBackground
                        )
                    }*/
                },
            )
        },
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(paddingValue)
        ) {
            if (model.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }

            model.error?.let { error ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = error)
                }
            }

            model.pokemon?.let { pokemonInfo ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(primaryColor)
                    ) {

                        Box {
                            Image(
                                painter = painterResource(Res.drawable.pokeballbackground),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.matchParentSize()
                            )

                            AsyncImage(
                                url = pokemonInfo.imageUrl,
                                contentDescription = pokemonInfo.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .widthIn(max = 350.dp)
                                    .fillMaxWidth()
                                    .aspectRatio(1.2f)
                            )
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(primaryColor)
                            .clip(
                                MaterialTheme.shapes.large.copy(
                                    bottomEnd = CornerSize(0.dp),
                                    bottomStart = CornerSize(0.dp)
                                )
                            )
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Spacer(modifier = Modifier.height(18.dp))

                        Text(
                            text = pokemonInfo.name.replaceFirstChar { it.uppercaseChar() },
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(.9f)
                        ) {

                            PokemonTypeSection(
                                types = pokemonInfo.types,
                            )

                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = pokemonInfo.idString,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                )

                                Text(
                                    text = pokemonInfo.genus,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = pokemonInfo.description,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        PokemonPhysicalSection(
                            pokemonInfo = pokemonInfo,
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                        )

                        PokemonSection(
                            pokemonInfo = pokemonInfo,
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                        )
                    }
                }
            }
        }
    }
}