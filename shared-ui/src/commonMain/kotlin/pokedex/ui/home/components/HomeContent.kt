package pokedex.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import pokedex.ui.home.state.CategoryState
import pokedex.ui.home.loop.HomeEvent
import pokedex.ui.home.loop.HomeModel
import pokedex.ui.pokedex.components.PokemonGrid

@Composable
internal fun HomeContent(
    model: HomeModel,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f)

    Column(
        modifier = modifier
    ) {
        Text(
            text = "What Pokémon are you looking for?",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
        )
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onEvent(HomeEvent.SearchPokedex(searchText)) }
            ),
            placeholder = { Text(text = "Search Pokemon") },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { onEvent(HomeEvent.SearchPokedex(searchText)) }
                ) {
                    Icon(Icons.Rounded.Search, contentDescription = "Search Pokemon")
                }
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            searchText = ""
                            onEvent(HomeEvent.ClearSearch)
                        }
                    ) {
                        Icon(Icons.Rounded.Close, contentDescription = "Clear search")
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        )

        if (model.searchQuery.isEmpty()) {
            CategoryButtons(onEvent)
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                PokemonGrid(
                    onPokemonClicked = { name -> onEvent(HomeEvent.NavigateToDetails(name)) },
                    pokemonList = model.results.orEmpty(),
                    isLoading = model.isLoading,
                    loadMoreItems = {
                        if (model.results.orEmpty().isNotEmpty()) {
                            onEvent(HomeEvent.LoadMoreResults)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryButtons(onEvent: (HomeEvent) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        CategoryButton(
            onClick = { onEvent(HomeEvent.OpenPokedex) },
            categoryState = CategoryState.pokedex,
            modifier = Modifier.weight(1f),
        )

        CategoryButton(
            onClick = { onEvent(HomeEvent.OpenComingSoon) },
            categoryState = CategoryState.moves,
            modifier = Modifier.weight(1f),
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        CategoryButton(
            onClick = { onEvent(HomeEvent.OpenComingSoon) },
            categoryState = CategoryState.evolutions,
            modifier = Modifier.weight(1f),
        )

        CategoryButton(
            onClick = { onEvent(HomeEvent.OpenComingSoon) },
            categoryState = CategoryState.locations,
            modifier = Modifier.weight(1f),
        )
    }
}