package pokedex.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import pokedex.ui.helper.LocalSafeArea
import pokedex.ui.home.components.HomeContent
import pokedex.ui.utils.rememberMobiusLoop
import kt.mobius.SimpleLogger
import kt.mobius.flow.FlowMobius
import pokedex.ui.home.loop.*


@Composable
fun HomeScreen() {
    val (modelState, eventConsumer) = rememberMobiusLoop(HomeModel(), HomeInit()) {
        FlowMobius.loop(HomeUpdate(), get<HomeEffectHandler>())
            .logger(SimpleLogger("Home Screen"))
    }

    Scaffold(
        topBar = {},
        modifier = Modifier.padding(LocalSafeArea.current)
    ) { paddingValues ->
        HomeContent(
            model = modelState.value,
            onEvent = eventConsumer,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

