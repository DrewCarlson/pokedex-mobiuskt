package pokedex.ui.home.loop

sealed class HomeEffect {
    data object NavigateToPokedex : HomeEffect()
}
