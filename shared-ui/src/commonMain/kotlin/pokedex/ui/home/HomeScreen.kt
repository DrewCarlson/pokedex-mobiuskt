package pokedex.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import pokedex.ui.helper.LocalSafeArea
import pokedex.ui.home.components.HomeContent
import pokedex.ui.home.components.HomeModalDrawerSheet
import pokedex.ui.utils.rememberMobiusLoop
import kotlinx.coroutines.launch
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius
import pokedex.ui.home.loop.*


@Composable
fun HomeScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoop(HomeModel(), HomeInit()) {
        FlowMobius.loop(HomeUpdate(), get<HomeEffectHandler>())
            .logger(SimpleLogger("Home Screen"))
    }

    val items = remember {
        listOf(
            "Home" to Icons.Outlined.Home,
            "Favorite" to Icons.Outlined.Favorite
        )
    }
    var selectedItem by remember { mutableStateOf(items[0]) }

    LaunchedEffect(selectedItem) {
        if (selectedItem.first == "Favorite") {
            eventConsumer(HomeEvent.OpenFavorites)
        }
    }

    BoxWithConstraints {
        if (maxWidth >= 1200.dp) {
            HomeContentLarge(
                state = modelState.value,
                onEvent = eventConsumer,
                items = items,
                selectedItem = selectedItem,
                updateSelectedItem = { selectedItem = it }
            )
        } else {
            HomeContentDefault(
                state = modelState.value,
                onEvent = eventConsumer,
                items = items,
                selectedItem = selectedItem,
                updateSelectedItem = { selectedItem = it }
            )
        }
    }
}


@Composable
internal fun HomeContentDefault(
    state: HomeModel,
    onEvent: (HomeEvent) -> Unit,
    items: List<Pair<String, ImageVector>>,
    selectedItem: Pair<String, ImageVector>,
    updateSelectedItem: (Pair<String, ImageVector>) -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeModalDrawerSheet(
                items = items,
                selectedItem = selectedItem,
                onItemsClick = { item ->
                    scope.launch { drawerState.close() }
                    updateSelectedItem(item)
                }
            )
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() }
                                },
                            ) {
                                Icon(Icons.Rounded.Menu, contentDescription = null)
                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )
                },
                modifier = Modifier.padding(LocalSafeArea.current)
            ) { paddingValues ->
                HomeContent(
                    model = state,
                    onEvent = onEvent,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}


@Composable
internal fun HomeContentLarge(
    state: HomeModel,
    onEvent: (HomeEvent) -> Unit,
    items: List<Pair<String, ImageVector>>,
    selectedItem: Pair<String, ImageVector>,
    updateSelectedItem: (Pair<String, ImageVector>) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        HomeModalDrawerSheet(
            items = items,
            selectedItem = selectedItem,
            onItemsClick = { item ->
                updateSelectedItem(item)
            }
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            modifier = Modifier.padding(LocalSafeArea.current)
        ) { paddingValues ->
            HomeContent(
                model = state,
                onEvent = onEvent,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}