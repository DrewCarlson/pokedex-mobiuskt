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

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = .4f)
                )

                if (state.isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = .6f),
                        trackColor = MaterialTheme.colorScheme.outline.copy(alpha = .4f),
                    )
                }

                PokemonGrid(
                    onPokemonClicked = { name -> onEvent(Event.NavigateToDetails(name)) },
                    pokemonList = state.pokemonList,
                    isLoading = state.isLoading,
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