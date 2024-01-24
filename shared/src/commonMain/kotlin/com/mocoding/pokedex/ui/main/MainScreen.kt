package com.mocoding.pokedex.ui.main

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
import com.mocoding.pokedex.ui.helper.LocalSafeArea
import com.mocoding.pokedex.ui.main.components.MainContent
import com.mocoding.pokedex.ui.main.components.MainModalDrawerSheet
import com.mocoding.pokedex.ui.main.loop.DashboardEffectHandler
import com.mocoding.pokedex.ui.main.loop.DashboardEvent
import com.mocoding.pokedex.ui.main.loop.DashboardModel
import com.mocoding.pokedex.ui.main.loop.DashboardUpdate
import com.mocoding.pokedex.ui.utils.rememberMobiusLoopPreCompose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kt.mobius.*
import kt.mobius.flow.FlowMobius
import kt.mobius.functions.Consumer
import moe.tlaster.precompose.viewmodel.ViewModel

class LoopHoldingViewModel<M, E, F>(
    private val loop: MobiusLoop<M, E, F>
) : ViewModel(), Consumer<M> {

    private val _model = MutableStateFlow(loop.mostRecentModel)
    val model = _model.asStateFlow()

    init {
        loop.observe(this)
    }

    fun dispatchEvent(event: E) {
        loop.dispatchEvent(event)
    }

    override fun accept(value: M) {
        _model.update { value }
    }

    override fun onCleared() {
        super.onCleared()
        loop.dispose()
    }
}

@Composable
fun MainScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoopPreCompose(DashboardModel()) {
        FlowMobius.loop(DashboardUpdate(), DashboardEffectHandler())
            .logger(SimpleLogger("Main Screen"))
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
            MainContentLarge(
                state = modelState.value,
                onEvent = eventConsumer,
                onOutput = {},
                items = items,
                selectedItem = selectedItem,
                updateSelectedItem = { selectedItem = it }
            )
        } else {
            MainContentDefault(
                state = modelState.value,
                onEvent = eventConsumer,
                onOutput = {},
                items = items,
                selectedItem = selectedItem,
                updateSelectedItem = { selectedItem = it }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContentDefault(
    state: DashboardModel,
    onEvent: (DashboardEvent) -> Unit,
    onOutput: () -> Unit,
    items: List<Pair<String, ImageVector>>,
    selectedItem: Pair<String, ImageVector>,
    updateSelectedItem: (Pair<String, ImageVector>) -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainModalDrawerSheet(
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
                MainContent(
                    state = state,
                    onEvent = onEvent,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContentLarge(
    state: DashboardModel,
    onEvent: (DashboardEvent) -> Unit,
    onOutput: () -> Unit,
    items: List<Pair<String, ImageVector>>,
    selectedItem: Pair<String, ImageVector>,
    updateSelectedItem: (Pair<String, ImageVector>) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        MainModalDrawerSheet(
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
            MainContent(
                state = state,
                onEvent = onEvent,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}