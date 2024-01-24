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
import pokedex.ui.home.loop.DashboardEffectHandler
import pokedex.ui.home.loop.DashboardEvent
import pokedex.ui.home.loop.DashboardModel
import pokedex.ui.home.loop.DashboardUpdate
import pokedex.ui.utils.rememberMobiusLoopPreCompose
import kotlinx.coroutines.launch
import kt.mobius.*
import kt.mobius.flow.FlowMobius


@Composable
fun HomeScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoopPreCompose(DashboardModel()) {
        FlowMobius.loop(DashboardUpdate(), DashboardEffectHandler())
            .logger(SimpleLogger("Home Screen"))
    }

    val items = listOf("Home" to Icons.Outlined.Home, "Favorite" to Icons.Outlined.Favorite)
    var selectedItem by remember { mutableStateOf(items[0]) }

    LaunchedEffect(selectedItem) {
        if (selectedItem.first == "Favorite") {
            //TODO: component.onOutput(MainComponent.Output.FavoriteClicked)
        }
    }

    BoxWithConstraints {
        if (maxWidth > 1199.dp) {
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
    state: DashboardModel,
    onEvent: (DashboardEvent) -> Unit,
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
                    state = state,
                    onEvent = onEvent,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}


@Composable
internal fun HomeContentLarge(
    state: DashboardModel,
    onEvent: (DashboardEvent) -> Unit,
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
                state = state,
                onEvent = onEvent,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}