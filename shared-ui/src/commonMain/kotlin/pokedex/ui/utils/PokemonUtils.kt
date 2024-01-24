package pokedex.ui.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import org.drewcarlson.`shared-ui`.generated.resources.Res
import pokedex.ui.theme.*

object PokemonUtils {

    @Stable
    fun getTypeColor2(name: String): Color = when (name) {
        "fighting" -> FightingLight
        "flying" -> FlyingLight
        "poison" -> PoisonLight
        "ground" -> GroundLight
        "rock" -> RockLight
        "bug" -> BugLight
        "ghost" -> GhostLight
        "steel" -> SteelLight
        "fire" -> FireLight
        "water" -> WaterLight
        "grass" -> GrassLight
        "electric" -> ElectricLight
        "psychic" -> PsychicLight
        "ice" -> IceLight
        "dragon" -> DragonLight
        "fairy" -> FairyLight
        "dark" -> DarkLight
        else -> Gray400
    }

    @Stable
    fun getTypeColor(name: String): Color = when (name) {
        "fighting" -> Fighting
        "flying" -> Flying
        "poison" -> Poison
        "ground" -> Ground
        "rock" -> Rock
        "bug" -> Bug
        "ghost" -> Ghost
        "steel" -> Steel
        "fire" -> Fire
        "water" -> Water
        "grass" -> Grass
        "electric" -> Electric
        "psychic" -> Psychic
        "ice" -> Ice
        "dragon" -> Dragon
        "fairy" -> Fairy
        "dark" -> Dark
        else -> Gray400
    }

    @Stable
    fun getTypeIcon(name: String) = when (name) {
        "fighting" -> Res.drawable.ic_fighting
        "flying" -> Res.drawable.ic_flying
        "poison" -> Res.drawable.ic_poison
        "ground" -> Res.drawable.ic_ground
        "rock" -> Res.drawable.ic_rock
        "bug" -> Res.drawable.ic_bug
        "ghost" -> Res.drawable.ic_ghost
        "steel" -> Res.drawable.ic_steel
        "fire" -> Res.drawable.ic_fire
        "water" -> Res.drawable.ic_water
        "grass" -> Res.drawable.ic_grass
        "electric" -> Res.drawable.ic_electric
        "psychic" -> Res.drawable.ic_psychic
        "ice" -> Res.drawable.ic_ice
        "dragon" -> Res.drawable.ic_dragon
        "fairy" -> Res.drawable.ic_fairy
        "dark" -> Res.drawable.ic_dark
        else -> Res.drawable.ic_dark
    }
}