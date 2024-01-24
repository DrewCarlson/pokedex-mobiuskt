package pokedex.ui.pokedex.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pokedex.ui.helper.LocalSafeArea
import pokedex.ui.pokedex.loop.PokedexModel
import pokedex.ui.pokedex.loop.PokedexEvent as Event


@Composable
internal fun PokedexContent(
    state: PokedexModel,
    onEvent: (Event) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(Event.NavigateBack)
                        },
                    ) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        modifier = Modifier.padding(LocalSafeArea.current)
    ) {  paddingValue ->
        Box(
            modifier = Modifier.padding(paddingValue)
        ) {

//            TextField(
//                value = state.search,
//                onValueChange = { newSearch ->
//                    onEvent(MainStore.Intent.InputPokemonSearch(newSearch))
//                },
//                placeholder = {
//                    Text(text = "Search Pokemon")
//                },
//                leadingIcon = {
//                    IconButton(
//                        onClick = {}
//                    ) {
//                        Icon(Icons.Rounded.Search, contentDescription = "Search Pokemon")
//                    }
//                },
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f),
//                    placeholderColor = MaterialTheme.colorScheme.surface,
//                    focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
//                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                ),
//                shape = MaterialTheme.shapes.extraLarge,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 20.dp)
//            )

            state.error?.let { error ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = error)
                }
            }

            Column {
                Text(
                    text = "Pokédex",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp, bottom = 6.dp)
                )

                Divider(
                    color = MaterialTheme.colorScheme.outline.copy(alpha = .4f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

                if (state.isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = .6f),
                        trackColor = MaterialTheme.colorScheme.outline.copy(alpha = .4f),
                    )
                }

                PokemonGrid(
                    onPokemonClicked = { name ->
                        onEvent(Event.NavigateToDetails(name = name))
                    },
                    pokemonList = state.pokemonList,
                    isLoading = !state.isLastPageLoaded,
                    loadMoreItems = {
                        if (state.pokemonList.isNotEmpty()) {
                            onEvent(Event.LoadPokemonList)
                        }
                    }
                )
            }
       }
    }
}