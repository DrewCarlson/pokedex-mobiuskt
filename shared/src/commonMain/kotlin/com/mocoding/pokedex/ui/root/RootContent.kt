package com.mocoding.pokedex.ui.root

import androidx.compose.runtime.Composable
import com.mocoding.pokedex.ui.comingsoon.ComingSoonScreen
import com.mocoding.pokedex.ui.details.DetailsScreen
import com.mocoding.pokedex.ui.favorite.FavoriteScreen
import com.mocoding.pokedex.ui.main.MainScreen
import com.mocoding.pokedex.ui.pokedex.PokedexScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path

@Composable
fun RootContent(navigator: Navigator) {
    NavHost(
        navigator = navigator,
        initialRoute = "/dashboard",
    ) {
        scene("/dashboard") {
            MainScreen()
        }
        scene("/pokedex") {
            PokedexScreen()
        }
        scene("/favorite") {
            FavoriteScreen()
        }
        scene("/details/{name}") {
            val name = checkNotNull(it.path<String>("name"))
            DetailsScreen(name)
        }
        scene("/coming-soon") {
            ComingSoonScreen()
        }
    }
}